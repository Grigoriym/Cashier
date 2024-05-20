package com.grappim.cashier.feature.productcategory.domain.repository

import com.grappim.cashier.common.lce.VoidTry
import com.grappim.cashier.feature.productcategory.domain.interactor.create.CreateProductCategoryParams
import com.grappim.cashier.feature.productcategory.domain.interactor.edit.EditProductCategoryParams
import com.grappim.cashier.feature.productcategory.domain.interactor.getCategoryList.GetCategoryListInteractorParams
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductCategoryRepository {

    fun categoriesFlow(): Flow<List<ProductCategory>>

    suspend fun filterCategories(offset: Long, limit: Long): List<ProductCategory>

    suspend fun insertCategories(newCategories: List<ProductCategory>)

    suspend fun createProductCategory(params: CreateProductCategoryParams): VoidTry<Throwable>

    suspend fun editProductCategory(params: EditProductCategoryParams): VoidTry<Throwable>

    fun getCategories2(params: GetCategoryListInteractorParams): Flow<List<ProductCategory>>

    fun getCategoriesInEditProducts(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>>
}
