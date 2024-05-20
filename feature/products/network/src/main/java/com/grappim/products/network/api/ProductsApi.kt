package com.grappim.products.network.api

import com.grappim.cashier.common.annotations.RequestWithAuthToken
import com.grappim.products.network.model.CreateProductRequestDTO
import com.grappim.products.network.model.CreateProductResponseDTO
import com.grappim.products.network.model.FilterProductsRequestDTO
import com.grappim.products.network.model.FilterProductsResponseDTO
import com.grappim.products.network.model.ProductDTO
import com.grappim.products.network.model.UpdateProductRequestDTO
import com.grappim.products.network.model.UpdateProductResponseDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductsApi {

    @GET("product/{productId}")
    @RequestWithAuthToken
    suspend fun getProductById(@Path("productId") productId: String): ProductDTO

    @POST("product")
    @RequestWithAuthToken
    suspend fun createProduct(
        @Body createProduct: CreateProductRequestDTO
    ): CreateProductResponseDTO

    @PUT("product")
    @RequestWithAuthToken
    suspend fun updateProduct(@Body product: UpdateProductRequestDTO): UpdateProductResponseDTO

    @DELETE("product/{productId}")
    @RequestWithAuthToken
    suspend fun deleteProduct(@Path("productId") id: String): CreateProductResponseDTO

    @POST("product/filter")
    @RequestWithAuthToken
    suspend fun filterProducts(
        @Body filterProductsRequestDTO: FilterProductsRequestDTO
    ): FilterProductsResponseDTO
}
