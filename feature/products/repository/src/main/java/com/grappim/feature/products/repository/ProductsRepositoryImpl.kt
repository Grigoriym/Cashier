package com.grappim.feature.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.asynchronous.runOperationCatching
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.comon.db.getStringForDbQuery
import com.grappim.date_time.DateTimeIsoLocalDateTime
import com.grappim.date_time.DateTimeUtils
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.ProductEntity
import com.grappim.db.entity.productEntityTableName
import com.grappim.db.helper.RoomQueryHelper
import com.grappim.domain.model.Product
import com.grappim.domain.model.ProductUnit
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.bag.db.BasketDao
import com.grappim.feature.bag.network.api.BasketApi
import com.grappim.feature.bag.network.di.QualifierBasketApi
import com.grappim.feature.products.domain.interactor.createProduct.CreateProductParams
import com.grappim.feature.products.domain.interactor.editProduct.EditProductParams
import com.grappim.feature.products.domain.interactor.getProductByBarcode.GetProductBarcodeParams
import com.grappim.feature.products.domain.interactor.getProductsByQuery.GetProductsByQueryParams
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.products.network.api.ProductsApi
import com.grappim.products.network.di.QualifierProductsApi
import com.grappim.products.network.mapper.toDomain
import com.grappim.products.network.mapper.toDomain2
import com.grappim.products.network.mapper.toEntity
import com.grappim.products.network.model.*
import com.grappim.products.network.paging.FilterProductsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AppScope
class ProductsRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val basketDao: BasketDao,
    private val generalStorage: GeneralStorage,
    @QualifierProductsApi private val productsApi: ProductsApi,
    @DateTimeIsoLocalDateTime private val dtfIsoLocal: DateTimeFormatter,
    @QualifierBasketApi private val basketApi: BasketApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProductsRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 30,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    override suspend fun getProductByBarcode(
        params: GetProductBarcodeParams
    ): Try<Product, Throwable> = runOperationCatching {
        val product = productsDao.getProductByBarcode(barcode = params.barcode)
            ?: error("not found")
        val domainToReturn = product.toDomain()
        domainToReturn
    }

    override fun getProductsByQuery(
        params: GetProductsByQueryParams
    ): Flow<List<Product>> =
        flow {
            val category = params.category
            val query = params.query
            val roomQuery = StringBuilder("SELECT * FROM $productEntityTableName ")
                .append("WHERE merchantId = '${generalStorage.getMerchantId()}' ")
                .append(
                    if (category == null || category.id == ProductCategory.ALL_DEFAULT_ID) {
                        ""
                    } else {
                        "AND categoryId = '${category.id}' "
                    }
                )
                .append(
                    if (query.isNotBlank()) {
                        "AND name LIKE '${query.getStringForDbQuery()}' "
                    } else {
                        ""
                    }
                )

            emitAll(
                productsDao.getProductsFlow(
                    RoomQueryHelper.toSQLiteQuery(roomQuery)
                ).map {
                    it.toDomain2()
                }
            )
        }

    override suspend fun createProduct(
        params: CreateProductParams
    ): Try<Unit, Throwable> = runOperationCatching {
        val response = productsApi.createProduct(
            createProduct = CreateProductRequestDTO(
                CreateProductRequestParamsDTO(
                    name = params.name,
                    stockId = params.stockId,
                    merchantId = params.merchantId,
                    unit = params.unit,
                    purchasePrice = params.purchasePrice,
                    sellingPrice = params.sellingPrice,
                    amount = params.amount,
                    barcode = params.barcode,
                    categoryId = params.categoryId
                )
            )
        )

        val domain = response.product.toEntity()

        productsDao.insert(
            ProductEntity(
                id = domain.id,
                barcode = params.barcode,
                name = params.name,
                stockId = params.stockId,
                amount = params.amount,
                unit = ProductUnit.getProductUnitByValue(params.unit),
                purchasePrice = params.purchasePrice,
                sellingPrice = params.sellingPrice,
                merchantId = params.merchantId,
                createdOn = domain.createdOn,
                updatedOn = domain.updatedOn,
                categoryId = domain.categoryId
            )
        )
    }

    override suspend fun updateProduct(
        params: EditProductParams
    ): Try<Unit, Throwable> = runOperationCatching {
        val productDTO = ProductDTO(
            id = params.productId,
            barcode = params.barcode,
            name = params.name,
            stockId = params.productStockId,
            amount = params.amount,
            unit = ProductUnit.getProductUnitByValue(params.unit.value),
            purchasePrice = params.purchasePrice,
            sellingPrice = params.sellingPrice,
            merchantId = params.productMerchantId,
            createdOn = params.productCreatedOn,
            updatedOn = dtfIsoLocal.format(DateTimeUtils.getNowOffsetDateTime(true)),
            categoryId = params.categoryId
        )

        val response = productsApi.updateProduct(
            UpdateProductRequestDTO(productDTO)
        )

        val entity = response.product.toEntity()
        productsDao.update(entity)
    }

    override fun searchProducts(
        params: com.grappim.feature.products.domain.interactor.searchProducts.SearchProductsParams
    ): Flow<PagingData<Product>> = Pager(
        config = pagingConfig
    ) {
        FilterProductsPagingSource(
            ioDispatcher = ioDispatcher,
            generalStorage = generalStorage,
            productsApi = productsApi,
            query = params.query,
            basketApi = basketApi,
            basketDao = basketDao
        )
    }.flow

    override suspend fun filterProducts(offset: Long, limit: Long): List<Product> {
        val request = FilterProductsRequestDTO(
            offset = offset,
            limit = limit,
            merchantId = generalStorage.getMerchantId(),
            stockId = generalStorage.stockId
        )

        val response = productsApi.filterProducts(request).products
        val mappedProducts = response.toDomain()
        return mappedProducts
    }

    override suspend fun insertProducts(newProducts: List<Product>) {
        val entities = newProducts.toEntity()
        productsDao.insert(entities)
    }
}