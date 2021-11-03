package com.grappim.repository.remote

import com.grappim.db.dao.BasketDao
import com.grappim.db.dao.CategoryDao
import com.grappim.db.dao.ProductsDao
import com.grappim.db.entity.CategoryEntity
import com.grappim.db.entity.productEntityTableName
import com.grappim.db.helper.RoomQueryHelper
import com.grappim.domain.base.Result
import com.grappim.domain.di.ApplicationScope
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.interactor.products.SearchProductsByCategoryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.mappers.category.CategoryMapper
import com.grappim.network.mappers.products.ProductMapper
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
import javax.inject.Singleton

@Singleton
class GeneralRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val productsDao: ProductsDao,
    private val categoryDao: CategoryDao,
    private val generalStorage: GeneralStorage,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val categoryMapper: CategoryMapper,
    private val productMapper: ProductMapper
) : GeneralRepository {

    override fun getCategories(
        params: GetCategoryListUseCase.Params
    ): Flow<Result<List<Category>>> = flow {
        emit(Result.Loading)
        val categories = categoryDao.getAllCategories().toMutableList()
        if (params.sendDefaultCategory) {
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
        val domain = categoryMapper.dbToDomainList(categories.toList())
        emit(Result.Success(domain))
    }

    override fun getCategories2(params: GetCategoryListUseCase.Params): Flow<List<Category>> =
        flow {
            val categories = categoryDao.getAllCategories().toMutableList()
            if (params.sendDefaultCategory) {
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
            val domain = categoryMapper.dbToDomainList(categories.toList())
            emit(domain)
        }

    override fun getProductsByCategory(
        params: SearchProductsByCategoryUseCase.Params
    ): Flow<Result<List<Product>>> = flow {
        emit(Result.Loading)
        val category = params.category
        val entities = if (category.isDefault) {
            productsDao.getAllProducts()
        } else {
            productsDao.searchProductsByCategoryId(category.id)
        }

        val domain = productMapper.entityToDomainList(entities)

        emit(Result.Success(domain))
    }

    override fun getProductsByQuery(
        params: GetProductsByQueryUseCase.Params
    ): Flow<List<Product>> =
        flow {
            val category = params.category
            val query = params.query
            val roomQuery = StringBuilder("SELECT * FROM $productEntityTableName ")
                .append("WHERE merchantId=${generalStorage.getMerchantId()} ")
                .append(
                    if (category == null || category.isDefault) {
                        ""
                    } else {
                        "AND categoryId = ${category.id} "
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
                    productMapper.entityToDomainList(it)
                }
            )
        }

    override suspend fun clearData() = withContext(ioDispatcher) {
        applicationScope.launch {
            generalStorage.clearData()
            basketDao.clearBasket()
            productsDao.clearProducts()
            categoryDao.clearCategories()
        }.join()
    }

}