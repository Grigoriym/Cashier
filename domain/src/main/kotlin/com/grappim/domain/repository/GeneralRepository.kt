package com.grappim.domain.repository

import com.grappim.domain.interactor.products.GetCategoryListInteractorParams
import com.grappim.domain.interactor.products.GetProductsByQueryParams
import com.grappim.domain.interactor.products.GetProductsByQueryUseCaseImpl
import com.grappim.domain.model.product.Product
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface GeneralRepository {

    fun getCategories2(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>>

    fun getCategoriesInEditProducts(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>>

    suspend fun clearData()

    fun getProductsByQuery(
        params: GetProductsByQueryParams
    ): Flow<List<Product>>

}