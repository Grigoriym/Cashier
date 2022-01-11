package com.grappim.product_category.network.api

import com.grappim.product_category.network.model.CreateProductCategoryRequestDTO
import com.grappim.product_category.network.model.CreateProductCategoryResponseDTO
import com.grappim.product_category.network.model.FilterProductCategoriesRequest
import com.grappim.product_category.network.model.ProductCategoryDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductCategoryApi {

    @POST("category/filter")
    suspend fun filterCategoryProducts(
        @Body request: FilterProductCategoriesRequest
    ): List<ProductCategoryDTO>

    @POST("category")
    suspend fun createCategory(
        @Body request: CreateProductCategoryRequestDTO
    ): CreateProductCategoryResponseDTO
}