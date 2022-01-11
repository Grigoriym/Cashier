package com.grappim.product_category.repository

import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.di.api.QualifierProductCategoryApi
import com.grappim.product_category.db.ProductCategoryDao
import com.grappim.product_category.db.ProductCategoryEntityMapper
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.product_category.network.api.ProductCategoryApi
import com.grappim.product_category.network.model.FilterProductCategoriesRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@AppScope
class ProductCategoryRepositoryImpl @Inject constructor(
    @QualifierProductCategoryApi private val productCategoryApi: ProductCategoryApi,
    private val productCategoryDao: ProductCategoryDao,
    private val productCategoryDTOMapper: ProductCategoryDTOMapper,
    private val generalStorage: GeneralStorage,
    private val productCategoryEntityMapper: ProductCategoryEntityMapper
) : ProductCategoryRepository {

    override fun getCategoriesFlow(): Flow<Try<List<ProductCategory>>> = flow {
        emit(Try.Loading)
        val result = getCategories()
        emit(Try.Success(result))
    }

    override fun getCategoriesFlow2(): Flow<List<ProductCategory>> = flow {
        emit(getCategories())
    }

    private suspend fun getCategories(): List<ProductCategory> {
        val result = productCategoryDao.getAllCategories()
        val mappedResult = productCategoryEntityMapper.revertList(result)
        return mappedResult
    }

    override suspend fun filterCategories(
        offset: Long,
        limit: Long
    ): List<ProductCategory> {
        val response = productCategoryApi.filterCategoryProducts(
            request = FilterProductCategoriesRequest(
                offset = offset,
                limit = limit,
                merchantId = generalStorage.getMerchantId(),
                stockId = generalStorage.stockId
            )
        )
        val mappedCategories = productCategoryDTOMapper.mapList(response)
        return mappedCategories
    }

    override suspend fun insertCategories(newCategories: List<ProductCategory>) {
        val entities = productCategoryEntityMapper.mapList(newCategories)
        productCategoryDao.insert(entities)
    }

}