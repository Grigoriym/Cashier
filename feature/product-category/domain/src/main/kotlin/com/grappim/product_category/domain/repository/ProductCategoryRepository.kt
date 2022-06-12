package com.grappim.product_category.domain.repository

import com.grappim.common.lce.VoidTry
import com.grappim.product_category.domain.interactor.CreateProductCategoryParams
import com.grappim.product_category.domain.interactor.EditProductCategoryParams
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductCategoryRepository {

    fun categoriesFlow(): Flow<List<ProductCategory>>

    suspend fun filterCategories(
        offset: Long,
        limit: Long
    ): List<ProductCategory>

    suspend fun insertCategories(newCategories: List<ProductCategory>)

    suspend fun createProductCategory(
        params: CreateProductCategoryParams
    ): VoidTry<Throwable>

    suspend fun editProductCategory(
        params: EditProductCategoryParams
    ): VoidTry<Throwable>

}