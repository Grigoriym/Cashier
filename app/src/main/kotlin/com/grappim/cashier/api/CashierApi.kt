package com.grappim.cashier.api

import com.grappim.cashier.core.network.RequestWithAuthToken
import com.grappim.cashier.data.remote.model.login.LoginResponseDTO
import com.grappim.cashier.data.remote.model.login.LoginRequestDTO
import com.grappim.cashier.data.remote.model.login.SendTokenToRefreshRequestDTO
import com.grappim.cashier.data.remote.model.outlet.GetOutletsResponseDTO
import com.grappim.cashier.data.remote.model.product.ProductDTO
import com.grappim.cashier.data.remote.model.product.CreateProductRequestDTO
import com.grappim.cashier.data.remote.model.product.GetProductsRequestDTO
import com.grappim.cashier.data.remote.model.product.UpdateProductResponseDTO
import com.grappim.cashier.data.remote.model.product.ProductIdResponseDTO
import com.grappim.cashier.data.remote.model.cashbox.GetCashBoxListRequestDTO
import com.grappim.cashier.data.remote.model.cashbox.GetCashBoxListResponseDTO
import com.grappim.cashier.data.remote.model.category.CategoryDTO
import com.grappim.cashier.data.remote.model.category.CreateCategoryRequestDTO
import com.grappim.cashier.data.remote.model.category.CreateCategoryResponseDTO
import com.grappim.cashier.data.remote.model.category.FilterCategoriesRequestDTO
import com.grappim.cashier.data.remote.model.category.FilterCategoriesResponseDTO
import com.grappim.cashier.data.remote.model.category.UpdateCategoryRequestDTO
import com.grappim.cashier.data.remote.model.order.CreateOrderRequestDTO
import com.grappim.cashier.data.remote.model.order.CreateOrderResponseDTO
import com.grappim.cashier.data.remote.model.product.GetProductsResponseDTO
import com.grappim.cashier.data.remote.model.product.UpdateProductRequestDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CashierApi {

    @POST("merch/refresh")
    suspend fun refreshToken(
        @Body request: SendTokenToRefreshRequestDTO
    ): SendTokenToRefreshRequestDTO

    @POST("merch/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDTO
    ): LoginResponseDTO

    @GET("stocks/list/{merchantId}")
    @RequestWithAuthToken
    suspend fun getStocks(
        @Path("merchantId") merchantId: String
    ): GetOutletsResponseDTO

    @GET("product/{productId}")
    @RequestWithAuthToken
    suspend fun getProductById(
        @Path("productId") productId: String
    ): ProductDTO

    @POST("product")
    @RequestWithAuthToken
    suspend fun createProduct(
        @Body createProduct: CreateProductRequestDTO
    ): ProductIdResponseDTO

    @PUT("product")
    @RequestWithAuthToken
    suspend fun updateProduct(
        @Body product: UpdateProductRequestDTO
    ): UpdateProductResponseDTO

    @DELETE("product/{productId}")
    @RequestWithAuthToken
    suspend fun deleteProduct(
        @Path("productId") id: String
    ): ProductIdResponseDTO

    @POST("product/filter")
    @RequestWithAuthToken
    suspend fun getProducts(
        @Body getProductsRequestDTO: GetProductsRequestDTO
    ): GetProductsResponseDTO

    @POST("cashbox/list")
    @RequestWithAuthToken
    suspend fun getCashBoxList(
        @Body getCashBoxListRequestDTO: GetCashBoxListRequestDTO
    ): GetCashBoxListResponseDTO

    @GET("category/{categoryId}")
    @RequestWithAuthToken
    suspend fun getCategoryById(
        @Path("categoryId") categoryId: String
    ): CategoryDTO

    @POST("category")
    @RequestWithAuthToken
    suspend fun createCategory(
        @Body createCategoryRequestDTO: CreateCategoryRequestDTO
    ): CreateCategoryResponseDTO

    @PUT("category")
    @RequestWithAuthToken
    suspend fun updateCategory(
        @Body updateCategoryRequestDTO: UpdateCategoryRequestDTO
    ): CreateCategoryResponseDTO

    @POST("category/filter")
    @RequestWithAuthToken
    suspend fun filterCategories(
        @Body filterCategoriesRequestDTO: FilterCategoriesRequestDTO
    ): FilterCategoriesResponseDTO

    @DELETE("category/{categoryId}")
    @RequestWithAuthToken
    suspend fun deleteCategoryById(
        @Path("categoryId") categoryId: String
    ): CreateCategoryResponseDTO

    @POST("product/sync")
    @RequestWithAuthToken
    suspend fun productsSync()

    @POST("order/create")
    @RequestWithAuthToken
    suspend fun createOrder(
        @Body createOrderRequestDTO: CreateOrderRequestDTO
    ): CreateOrderResponseDTO
}