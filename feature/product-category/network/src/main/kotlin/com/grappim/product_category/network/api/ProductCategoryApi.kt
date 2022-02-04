package com.grappim.product_category.network.api

import com.grappim.common.annotations.RequestWithAuthToken
import com.grappim.product_category.network.model.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ProductCategoryApi {

    @POST("category/filter")
    @RequestWithAuthToken
    suspend fun filterCategoryProducts(
        @Body request: FilterProductCategoriesRequestDTO
    ): FilterProductCategoryResponseDTO

    @POST("category")
    @RequestWithAuthToken
    suspend fun createCategory(
        @Body request: CreateProductCategoryRequestDTO
    ): CreateProductCategoryResponseDTO

    @PUT("category")
    @RequestWithAuthToken
    suspend fun editCategory(
        @Body request: EditProductCategoryRequestDTO
    ): ProductCategoryDTO
}