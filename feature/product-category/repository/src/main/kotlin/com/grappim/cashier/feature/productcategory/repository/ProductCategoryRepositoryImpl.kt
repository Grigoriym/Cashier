package com.grappim.cashier.feature.productcategory.repository

import com.grappim.cashier.common.di.AppScope
import com.grappim.cashier.common.lce.VoidTry
import com.grappim.cashier.common.lce.runOperationCatching
import com.grappim.cashier.feature.productcategory.db.ProductCategoryDao
import com.grappim.cashier.feature.productcategory.db.ProductCategoryEntityMapper
import com.grappim.cashier.feature.productcategory.domain.interactor.create.CreateProductCategoryParams
import com.grappim.cashier.feature.productcategory.domain.interactor.edit.EditProductCategoryParams
import com.grappim.cashier.feature.productcategory.domain.interactor.getCategoryList.GetCategoryListInteractorParams
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import com.grappim.cashier.feature.productcategory.network.api.ProductCategoryApi
import com.grappim.cashier.feature.productcategory.network.model.CreateProductCategoryDTO
import com.grappim.cashier.feature.productcategory.network.model.CreateProductCategoryRequestDTO
import com.grappim.cashier.feature.productcategory.network.model.EditProductCategoryRequestDTO
import com.grappim.cashier.feature.productcategory.network.model.FilterProductCategoriesRequestDTO
import com.grappim.cashier.feature.productcategory.network.model.ProductCategoryDTO
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.di.api.QualifierProductCategoryApi
import kotlinx.coroutines.flow.Flow
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

    override fun getCategories2(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> = productCategoryDao.getAllCategoriesFlow()
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
    ): Flow<List<ProductCategory>> = productCategoryDao.getAllCategoriesFlow()
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

    override fun categoriesFlow(): Flow<List<ProductCategory>> =
        productCategoryDao.getAllCategoriesFlow()
            .map {
                productCategoryEntityMapper.revertList(it)
            }

    @Suppress("UnusedPrivateMember")
    private suspend fun getCategories(): List<ProductCategory> {
        val result = productCategoryDao.getAllCategories()
        val mappedResult = productCategoryEntityMapper.revertList(result)
        return mappedResult
    }

    override suspend fun filterCategories(offset: Long, limit: Long): List<ProductCategory> {
        val response = productCategoryApi.filterCategoryProducts(
            request = FilterProductCategoriesRequestDTO(
                offset = offset,
                limit = limit,
                merchantId = generalStorage.getMerchantId(),
                stockId = generalStorage.stockId
            )
        )
        val mappedCategories = productCategoryDTOMapper.mapList(response.categories)
        return mappedCategories
    }

    private suspend fun insertCreatedCategory(id: Long, name: String) {
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

    override suspend fun createProductCategory(
        params: CreateProductCategoryParams
    ): VoidTry<Throwable> = runOperationCatching {
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
    }

    override suspend fun editProductCategory(
        params: EditProductCategoryParams
    ): VoidTry<Throwable> = runOperationCatching {
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
    }
}
