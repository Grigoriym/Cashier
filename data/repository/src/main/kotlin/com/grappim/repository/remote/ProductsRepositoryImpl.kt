package com.grappim.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.date_time.DateTimeIsoLocalDateTime
import com.grappim.date_time.DateTimeUtils
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.ProductEntity
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.BasketApi
import com.grappim.network.api.ProductsApi
import com.grappim.network.di.api.QualifierBasketApi
import com.grappim.network.di.api.QualifierProductsApi
import com.grappim.network.mappers.products.toDomain
import com.grappim.network.mappers.products.toEntity
import com.grappim.network.model.products.*
import com.grappim.repository.paging.FilterProductsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override fun createProduct(
        params: CreateProductUseCase.Params
    ): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
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

        emit(Try.Success(Unit))
    }

    override fun updateProduct(
        params: EditProductUseCase.Params
    ): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
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

        emit(Try.Success(Unit))
    }

    override fun searchProducts(
        params: SearchProductsUseCase.Params
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