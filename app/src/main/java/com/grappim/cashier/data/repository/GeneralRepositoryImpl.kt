package com.grappim.cashier.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.grappim.cashier.R
import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.extensions.bigDecimalZero
import com.grappim.cashier.core.extensions.getStringForDbQuery
import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.core.functional.map
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.core.utils.ProductUnit
import com.grappim.cashier.data.db.dao.BasketDao
import com.grappim.cashier.data.db.dao.CategoryDao
import com.grappim.cashier.data.db.dao.ProductsDao
import com.grappim.cashier.data.db.entity.*
import com.grappim.cashier.data.db.entity.ProductEntityMapper.toBasketProduct
import com.grappim.cashier.data.remote.BaseRepository
import com.grappim.cashier.data.remote.model.order.CreateOrderDTO
import com.grappim.cashier.data.remote.model.order.CreateOrderRequestDTO
import com.grappim.cashier.data.remote.model.order.OrderDTO
import com.grappim.cashier.data.remote.model.order.OrderItemDTO
import com.grappim.cashier.data.remote.model.product.CreateProductRequestDTO
import com.grappim.cashier.data.remote.model.product.ProductDTO
import com.grappim.cashier.data.remote.model.product.ProductsMapper.toDomain
import com.grappim.cashier.data.remote.model.product.UpdateProductRequestDTO
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.di.modules.QualifierCashierApi
import com.grappim.cashier.domain.products.CreateProductUseCase
import com.grappim.cashier.domain.products.EditProductUseCase
import com.grappim.cashier.domain.repository.GeneralRepository
import com.grappim.cashier.ui.menu.MenuItem
import com.grappim.cashier.ui.menu.MenuItemType
import com.grappim.cashier.ui.paymentmethod.PaymentMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val basketDao: BasketDao,
    private val productsDao: ProductsDao,
    private val categoryDao: CategoryDao,
    private val generalStorage: GeneralStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GeneralRepository, BaseRepository() {

    override suspend fun createProduct(
        params: CreateProductUseCase.CreateProductParams
    ): Either<Throwable, Unit> =
        apiCall {
            val response = cashierApi.createProduct(
                CreateProductRequestDTO(params)
            )
            withContext(ioDispatcher) {
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
            }
        }

    override suspend fun updateProduct(
        params: EditProductUseCase.UpdateProductParams
    ): Either<Throwable, Unit> =
        apiCall {
            val productDTO = ProductDTO(
                id = params.productEntity.id,
                barcode = params.barcode,
                name = params.name,
                stockId = params.productEntity.stockId,
                amount = params.amount,
                unit = params.unit.value,
                purchasePrice = params.purchasePrice,
                sellingPrice = params.sellingPrice,
                merchantId = params.productEntity.merchantId,
                createdOn = params.productEntity.createdOn,
                updatedOn = DateTimeUtils.getNowFullDate(),
                categoryId = params.categoryEntity?.id ?: 0,
                category = params.categoryEntity?.name ?: ""
            )
            val response = cashierApi.updateProduct(
                UpdateProductRequestDTO(productDTO)
            )

            withContext(ioDispatcher) {
                productsDao.update(
                    response.product.toDomain()
                )
            }
        }

    override suspend fun getCategories(
        sendDefaultCategory: Boolean
    ): Either<Throwable, List<CategoryEntity>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val categories = categoryDao.getAllCategories().toMutableList()
                if (sendDefaultCategory) {
                    categories.add(
                        0,
                        CategoryEntity(
                            id = -1,
                            name = "All",
                            merchantId = "",
                            stockId = "",
                            isDefault = true
                        )
                    )
                }
                Either.Right(categories)
            } catch (e: Throwable) {
                Either.Right(listOf())
            }
        }

    override suspend fun getProducts(): Either<Throwable, List<ProductEntity>> =
        Either.Right(getProductList())

    override suspend fun getProductsByCategory(categoryEntity: CategoryEntity): List<ProductEntity> =
        withContext(ioDispatcher) {
            if (categoryEntity.isDefault) {
                productsDao.getAllProducts()
            } else {
                productsDao.searchProductsByCategoryId(categoryEntity.id)
            }
        }

    override suspend fun clearBasket() = withContext(ioDispatcher) {
        basketDao.clearBasket()
    }

    override suspend fun addBasketProduct(productEntity: ProductEntity) =
        withContext(ioDispatcher) {
            basketDao.insertOrUpdate(productEntity.toBasketProduct())
        }

    override suspend fun removeBasketProduct(productEntity: ProductEntity) =
        withContext(ioDispatcher) {
            if (productEntity.basketCount <= bigDecimalZero()) {
                basketDao.removeProductByUid(productEntity.id)
            } else {
                basketDao.updateBasketProduct(productEntity.toBasketProduct())
            }
        }

    override fun getAllBasketProducts(): Flow<List<BasketProductEntity>> =
        basketDao.getAllBasketProducts()

    override fun getMenuItems(): Flow<List<MenuItem>> = flow {
        emit(
            listOf(
                MenuItem(
                    type = MenuItemType.SALES,
                    text = R.string.title_sales,
                    image = R.drawable.ic_cash_register
                ),
                MenuItem(
                    type = MenuItemType.PRODUCTS,
                    text = R.string.title_products,
                    image = R.drawable.ic_store
                ),
                MenuItem(
                    type = MenuItemType.ACCEPTANCE,
                    text = R.string.title_acceptance,
                    image = R.drawable.ic_store_acceptance
                )
            )
        )
    }

    override fun getProductsByQuery(
        categoryEntity: CategoryEntity?,
        query: String
    ): Flow<List<ProductEntity>> {
        val roomQuery = StringBuilder("SELECT * FROM $productEntityTableName ")
            .append("WHERE merchantId=${generalStorage.getMerchantId()} ")
            .append(
                if (categoryEntity == null || categoryEntity.isDefault) {
                    ""
                } else {
                    "AND categoryId = ${categoryEntity.id} "
                }
            )
            .append(
                if (query.isNotBlank()) {
                    "AND name LIKE '${query.getStringForDbQuery()}' "
                } else {
                    ""
                }
            )
        return productsDao.getProductsFlow(
            SimpleSQLiteQuery(roomQuery.toString())
        )
    }

    override suspend fun searchProducts(query: String): List<ProductEntity> =
        withContext(ioDispatcher) {
            val products = productsDao.searchProducts(query.getStringForDbQuery())

            val productsUids = products.map { it.id }
            val storedBasketProducts = basketDao.getProductsByUids(productsUids)

            return@withContext if (storedBasketProducts.isEmpty()) {
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
        }

    override suspend fun clearData() = withContext(ioDispatcher) {
        generalStorage.clearData()
        basketDao.clearBasket()
        productsDao.clearProducts()
        categoryDao.clearCategories()
    }

    override suspend fun getBagProducts(): List<ProductEntity> =
        withContext(ioDispatcher) {
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
            return@withContext result
        }

    override suspend fun deleteBagProducts() = withContext(ioDispatcher) {
        basketDao.deleteBagProducts()
    }

    override suspend fun makePayment(
        paymentMethod: PaymentMethod
    ): Either<Throwable, Unit> =
        apiCall {
            val totalSum = basketDao.getBasketProducts().map {
                it.sellingPrice * it.basketCount
            }.sumOf {
                it
            }

            val orderItems = basketDao.getBasketProducts().map {
                OrderItemDTO(
                    productId = it.id,
                    amount = it.basketCount,
                    sellingPrice = it.sellingPrice,
                    purchasePrice = it.purchasePrice
//                    barcode = it.barcode
                )
            }

            cashierApi.createOrder(
                CreateOrderRequestDTO(
                    order = CreateOrderDTO(
                        merchantId = generalStorage.getMerchantId(),
                        stockId = generalStorage.getStockId(),
                        cashBoxId = generalStorage.getCashierId(),
                        totalSum = totalSum,
                        payType = paymentMethod.type.name,
                        orderItems = orderItems
                    )
                )
            )
        }.map {
            basketDao.deleteBagProducts()
        }

    private suspend fun getProductList(): List<ProductEntity> =
        withContext(ioDispatcher) {
            val products = productsDao.getAllProducts()

            val productsUids = products.map { it.id }
            val storedBasketProducts = basketDao.getProductsByUids(productsUids)

            return@withContext if (storedBasketProducts.isEmpty()) {
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
        }

}