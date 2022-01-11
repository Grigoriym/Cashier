package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.interactor.products.SearchProductsByCategoryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface GeneralRepository {

    fun getCategories(
        params: GetCategoryListUseCase.Params
    ): Flow<Try<List<Category>>>

    fun getCategories2(
        params: GetCategoryListUseCase.Params
    ): Flow<List<Category>>

    fun getProductsByCategory(
        params: SearchProductsByCategoryUseCase.Params
    ): Flow<Try<List<Product>>>

    suspend fun clearData()

    fun getProductsByQuery(
        params: GetProductsByQueryUseCase.Params
    ): Flow<List<Product>>

}