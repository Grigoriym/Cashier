package com.grappim.product_category.domain.repository

import com.grappim.common.lce.Try
import com.grappim.product_category.domain.interactor.CreateProductCategoryUseCase
import com.grappim.product_category.domain.interactor.EditProductCategoryUseCase
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductCategoryRepository {

    fun getCategoriesFlow(): Flow<Try<List<ProductCategory>>>

    fun getCategoriesFlow2(): Flow<List<ProductCategory>>

    fun categoriesFlow(): Flow<List<ProductCategory>>

    suspend fun filterCategories(
        offset: Long,
        limit: Long
    ): List<ProductCategory>

    suspend fun insertCategories(newCategories: List<ProductCategory>)

    fun createProductCategory(
        params: CreateProductCategoryUseCase.InParams
    ): Flow<Try<Unit>>

    fun editProductCategory(
        params: EditProductCategoryUseCase.Params
    ): Flow<Try<Unit>>

}