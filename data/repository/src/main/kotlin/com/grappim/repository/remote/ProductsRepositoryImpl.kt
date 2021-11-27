package com.grappim.repository.remote

import com.grappim.calculations.bigDecimalZero
import com.grappim.date_time.DateTimeUtils
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.ProductEntity
import com.grappim.domain.base.Try
import com.grappim.domain.di.ApplicationScope
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import com.grappim.logger.logD
import com.grappim.network.api.CashierApi
import com.grappim.network.di.QualifierCashierApi
import com.grappim.network.mappers.products.ProductMapper
import com.grappim.network.model.products.CreateProductRequestDTO
import com.grappim.network.model.products.CreateProductRequestParamsDTO
import com.grappim.network.model.products.ProductDTO
import com.grappim.network.model.products.UpdateProductRequestDTO
import com.grappim.repository.extensions.getStringForDbQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val productMapper: ProductMapper,
    private val basketDao: BasketDao,
    @QualifierCashierApi private val cashierApi: CashierApi,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ProductsRepository {

    override fun createProduct(
        params: CreateProductUseCase.Params
    ): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
        val response = cashierApi.createProduct(
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
                    createdOn = params.createdOn,
                    updatedOn = params.createdOn,
                    categoryName = params.categoryName,
                    categoryId = params.categoryId
                )
            )
        )

        productsDao.insert(
            ProductEntity(
                id = response.id,
                barcode = params.barcode,
                name = params.name,
                stockId = params.stockId,
                amount = params.amount,
                unit = ProductUnit.getProductUnitByValue(params.unit),
                purchasePrice = params.purchasePrice,
                sellingPrice = params.sellingPrice,
                merchantId = params.merchantId,
                createdOn = params.createdOn,
                updatedOn = params.updatedOn,
                categoryId = 0,
                categoryName = ""
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
            unit = params.unit.value,
            purchasePrice = params.purchasePrice,
            sellingPrice = params.sellingPrice,
            merchantId = params.productMerchantId,
            createdOn = params.productCreatedOn,
            updatedOn = DateTimeUtils.getNowFullDate(),
            categoryId = params.categoryId,
            category = params.category
        )

        val response = cashierApi.updateProduct(
            UpdateProductRequestDTO(productDTO)
        )

        val entity = productMapper.dtoToEntity(response.product)
        productsDao.update(entity)

        emit(Try.Success(Unit))
    }

    override fun getBagProducts(): Flow<Try<List<Product>>> =
        flow {
            emit(Try.Loading)
            val basketProducts = basketDao.getBasketProducts()
            val products = productsDao.getAllProducts()
            val result = mutableListOf<ProductEntity>()
            basketProducts.forEach { basketProduct ->
                products.forEach { product ->
                    if (basketProduct.id == product.id) {
                        result.add(product.apply {
                            basketCount = basketProduct.basketCount
                        })
                    }
                }
            }
            val domain = productMapper.entityToDomainList(result.toList())
            emit(Try.Success(domain))
        }

    override suspend fun addBasketProduct(
        params: AddProductToBasketUseCase.Params
    ) =
        applicationScope.launch {
            val basketEntity = productMapper.domainToBasketEntity(params.product)
            basketDao.insertOrUpdate(basketEntity)
        }.join()

    override suspend fun removeBasketProduct(params: RemoveProductUseCase.Params) =
        applicationScope.launch {
            val product = params.product
            if (product.basketCount <= bigDecimalZero()) {
                basketDao.removeProductByUid(product.id)
            } else {
                val basketProduct = productMapper.domainToBasketEntity(product)
                basketDao.updateBasketProduct(basketProduct)
            }
        }.join()

    override fun getAllBasketProducts(): Flow<List<BasketProduct>> =
        basketDao.getAllBasketProducts().map {
            productMapper.entityToBasketDomainList(it)
        }

    override fun getProducts(): Flow<List<Product>> =
        flow {
            val products = productsDao.getAllProducts()

            val productsUids = products.map { it.id }
            val storedBasketProducts = basketDao.getProductsByUids(productsUids)

            val result = if (storedBasketProducts.isEmpty()) {
                products
            } else {
                val resultList: List<ProductEntity> = products
                resultList.forEach { product ->
                    storedBasketProducts.forEach { storedProduct ->
                        if (storedProduct.id == product.id) {
                            product.basketCount = storedProduct.basketCount
                        }
                    }
                }
                resultList
            }
            val domain = productMapper.entityToDomainList(result)
            emit(domain)
        }

    override suspend fun deleteBasketProducts() = applicationScope.launch {
        basketDao.deleteBagProducts()
    }.join()

    override fun searchProducts(
        params: SearchProductsUseCase.Params
    ): Flow<List<Product>> =
        flow {
            val query = params.query
            logD("searchingProducts query: $query")
            val products = if (query.isBlank()) {
                productsDao.getAllProducts()
            } else {
                productsDao.searchProducts(query.getStringForDbQuery())
            }
            logD("foundProducts: ${products.joinToString()}")

            val productsUids = products.map { it.id }
            val storedBasketProducts = basketDao.getProductsByUids(productsUids)

            val result = if (storedBasketProducts.isEmpty()) {
                products
            } else {
                val resultList: List<ProductEntity> = products
                resultList.forEach { product ->
                    storedBasketProducts.forEach { storedProduct ->
                        if (storedProduct.id == product.id) {
                            product.basketCount = storedProduct.basketCount
                        }
                    }
                }
                resultList
            }

            val domain = productMapper.entityToDomainList(result)
            emit(domain)
        }
}