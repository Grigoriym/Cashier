package com.grappim.repository.remote

import com.grappim.common.asynchronous.di.ApplicationScope
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.di.AppScope
import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.productEntityTableName
import com.grappim.db.helper.RoomQueryHelper
import com.grappim.domain.interactor.products.GetCategoryListInteractorParams
import com.grappim.domain.interactor.products.GetProductsByQueryParams
import com.grappim.domain.interactor.products.GetProductsByQueryUseCaseImpl
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.mappers.products.toDomain2
import com.grappim.product_category.db.ProductCategoryDao
import com.grappim.product_category.db.ProductCategoryEntityMapper
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.repository.extensions.getStringForDbQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
class GeneralRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val productsDao: ProductsDao,
    private val productCategoryDao: ProductCategoryDao,
    private val generalStorage: GeneralStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val productCategoryEntityMapper: ProductCategoryEntityMapper
) : GeneralRepository {

    override fun getCategories2(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> =
        productCategoryDao.getAllCategoriesFlow()
            .map {
                productCategoryEntityMapper.revertList(it).toMutableList()
            }.map {
                if (params.sendDefaultCategory) {
                    it.add(
                        index = 0,
                        element = ProductCategory.allItem()
                    )
                }
                it.toList()
            }

    override fun getCategoriesInEditProducts(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> =
        productCategoryDao.getAllCategoriesFlow()
            .map {
                productCategoryEntityMapper.revertList(it).toMutableList()
            }.map {
                it.add(0, ProductCategory.createCategory())
                if (params.sendDefaultCategory) {
                    it.add(
                        index = 0,
                        element = ProductCategory.allItem()
                    )
                }
                it.toList()
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

    override suspend fun clearData() = withContext(ioDispatcher) {
        applicationScope.launch {
            generalStorage.clearData()
            basketDao.clearBasket()
            productsDao.clearProducts()
        }.join()
    }

}