package com.grappim.cashier.feature.productcategory.network.api

import com.grappim.cashier.common.annotations.RequestWithAuthToken
import com.grappim.cashier.feature.productcategory.network.model.CreateProductCategoryRequestDTO
import com.grappim.cashier.feature.productcategory.network.model.CreateProductCategoryResponseDTO
import com.grappim.cashier.feature.productcategory.network.model.EditProductCategoryRequestDTO
import com.grappim.cashier.feature.productcategory.network.model.FilterProductCategoriesRequestDTO
import com.grappim.cashier.feature.productcategory.network.model.FilterProductCategoryResponseDTO
import com.grappim.cashier.feature.productcategory.network.model.ProductCategoryDTO
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
    suspend fun editCategory(@Body request: EditProductCategoryRequestDTO): ProductCategoryDTO
}
