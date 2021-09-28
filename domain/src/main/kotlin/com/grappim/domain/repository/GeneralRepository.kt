package com.grappim.domain.repository

import com.grappim.domain.base.Result
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.interactor.products.SearchProductsByCategoryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface GeneralRepository {

    fun getCategories(
        params: GetCategoryListUseCase.Params
    ): Flow<Result<List<Category>>>

    fun getProductsByCategory(
        params: SearchProductsByCategoryUseCase.Params
    ): Flow<Result<List<Product>>>

    suspend fun clearData()

    fun getProductsByQuery(
        params:GetProductsByQueryUseCase.Params
    ): Flow<List<Product>>

}