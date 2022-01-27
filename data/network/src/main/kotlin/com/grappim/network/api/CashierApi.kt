package com.grappim.network.api

import com.grappim.common.asynchronous.RequestWithAuthToken
import com.grappim.network.model.cashbox.GetCashBoxListRequestDTO
import com.grappim.network.model.cashbox.GetCashBoxListResponseDTO
import com.grappim.network.model.payment.CreateOrderRequestDTO
import com.grappim.network.model.payment.CreateOrderResponseDTO
import com.grappim.network.model.products.*
import com.grappim.network.model.stock.GetStocksResponseDTO
import retrofit2.http.*

interface CashierApi {

    @GET("stocks/list/{merchantId}")
    @RequestWithAuthToken
    suspend fun getStocks(
        @Path("merchantId") merchantId: String
    ): GetStocksResponseDTO

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

    @POST("cashbox/list")
    @RequestWithAuthToken
    suspend fun getCashBoxList(
        @Body getCashBoxListRequestDTO: GetCashBoxListRequestDTO
    ): GetCashBoxListResponseDTO

//    @GET("category/{categoryId}")
//    @RequestWithAuthToken
//    suspend fun getCategoryById(
//        @Path("categoryId") categoryId: String
//    ): CategoryDTO

//    @POST("category")
//    @RequestWithAuthToken
//    suspend fun createCategory(
//        @Body createCategoryRequestDTO: CreateCategoryRequestDTO
//    ): CreateCategoryResponseDTO

//    @PUT("category")
//    @RequestWithAuthToken
//    suspend fun updateCategory(
//        @Body updateCategoryRequestDTO: UpdateCategoryRequestDTO
//    ): CreateCategoryResponseDTO

//    @POST("category/filter")
//    @RequestWithAuthToken
//    suspend fun filterCategories(
//        @Body filterCategoriesRequestDTO: FilterCategoriesRequestDTO
//    ): FilterCategoriesResponseDTO

//    @DELETE("category/{categoryId}")
//    @RequestWithAuthToken
//    suspend fun deleteCategoryById(
//        @Path("categoryId") categoryId: String
//    ): CreateCategoryResponseDTO

    @POST("product/sync")
    @RequestWithAuthToken
    suspend fun productsSync()

    @POST("order/create")
    @RequestWithAuthToken
    suspend fun createOrder(
        @Body createOrderRequestDTO: CreateOrderRequestDTO
    ): CreateOrderResponseDTO
}