package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.products.GetCategoryListInteractor
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.interactor.products.SearchProductsByCategoryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface GeneralRepository {

    fun getCategories(
        params: GetCategoryListInteractor.Params
    ): Flow<Try<List<ProductCategory>>>

    fun getCategories2(
        params: GetCategoryListInteractor.Params
    ): Flow<List<ProductCategory>>

    fun getProductsByCategory(
        params: SearchProductsByCategoryUseCase.Params
    ): Flow<Try<List<Product>>>

    suspend fun clearData()

    fun getProductsByQuery(
        params: GetProductsByQueryUseCase.Params
    ): Flow<List<Product>>

}