package com.grappim.product_category.repository

import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.di.api.QualifierProductCategoryApi
import com.grappim.product_category.db.ProductCategoryDao
import com.grappim.product_category.db.ProductCategoryEntityMapper
import com.grappim.product_category.domain.interactor.CreateProductCategoryUseCase
import com.grappim.product_category.domain.interactor.EditProductCategoryUseCase
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.product_category.network.api.ProductCategoryApi
import com.grappim.product_category.network.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    override fun categoriesFlow(): Flow<List<ProductCategory>> =
        productCategoryDao.getAllCategoriesFlow()
            .map {
                productCategoryEntityMapper.revertList(it)
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

    private suspend fun insertCreatedCategory(
        id: Long,
        name: String
    ) {
        insertCategory(
            ProductCategory(
                id = id,
                name = name,
                merchantId = generalStorage.getMerchantId(),
                stockId = generalStorage.stockId
            )
        )
    }

    private suspend fun insertCategory(newCategory: ProductCategory) {
        val entity = productCategoryEntityMapper.map(newCategory)
        productCategoryDao.insert(entity)
    }

    override suspend fun insertCategories(newCategories: List<ProductCategory>) {
        val entities = productCategoryEntityMapper.mapList(newCategories)
        productCategoryDao.insert(entities)
    }

    override fun createProductCategory(
        params: CreateProductCategoryUseCase.InParams
    ): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
        val response = productCategoryApi.createCategory(
            CreateProductCategoryRequestDTO(
                category = CreateProductCategoryDTO(
                    name = params.name,
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.stockId
                )
            )
        )

        insertCreatedCategory(
            id = response.id,
            name = params.name
        )

        emit(Try.Success(Unit))
    }

    override fun editProductCategory(
        params: EditProductCategoryUseCase.Params
    ): Flow<Try<Unit>> = flow {
        emit(Try.Loading)
        val response = productCategoryApi.editCategory(
            EditProductCategoryRequestDTO(
                category = ProductCategoryDTO(
                    id = params.productCategory.id,
                    name = params.newName,
                    merchantId = params.productCategory.merchantId,
                    stockId = params.productCategory.stockId
                )
            )
        )

        insertCategory(productCategoryDTOMapper.map(response))

        emit(Try.Success(Unit))
    }


}