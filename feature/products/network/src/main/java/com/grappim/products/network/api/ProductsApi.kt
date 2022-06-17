package com.grappim.products.network.api

import com.grappim.common.annotations.RequestWithAuthToken
import com.grappim.products.network.model.*
import retrofit2.http.*

interface ProductsApi {

    @GET("product/{productId}")
    @RequestWithAuthToken
    suspend fun getProductById(
        @Path("productId") productId: String
    ): ProductDTO

    @POST("product")
    @RequestWithAuthToken
    suspend fun createProduct(
        @Body createProduct: CreateProductRequestDTO
    ): CreateProductResponseDTO

    @PUT("product")
    @RequestWithAuthToken
    suspend fun updateProduct(
        @Body product: UpdateProductRequestDTO
    ): UpdateProductResponseDTO

    @DELETE("product/{productId}")
    @RequestWithAuthToken
    suspend fun deleteProduct(
        @Path("productId") id: String
    ): CreateProductResponseDTO

    @POST("product/filter")
    @RequestWithAuthToken
    suspend fun filterProducts(
        @Body filterProductsRequestDTO: FilterProductsRequestDTO
    ): FilterProductsResponseDTO

}