package com.grappim.network.api

import com.grappim.common.annotations.RequestWithAuthToken
import com.grappim.network.model.basket.AddBasketProductDTO
import com.grappim.network.model.basket.BasketProductDTO
import com.grappim.network.model.basket.SearchProductsRequestDTO
import com.grappim.network.model.basket.SubtractBasketProductResponseDTO
import retrofit2.http.*

interface BasketApi {

    @POST("basket/add")
    @RequestWithAuthToken
    suspend fun addProductToBasket(
        @Body request: AddBasketProductDTO
    ): BasketProductDTO

    @POST("basket/subtract")
    @RequestWithAuthToken
    suspend fun subtractProduct(
        @Body request: BasketProductDTO
    ): SubtractBasketProductResponseDTO

    @POST("basket/remove")
    @RequestWithAuthToken
    suspend fun removeProduct(
        @Body request: BasketProductDTO
    )

    @GET("basket")
    @RequestWithAuthToken
    suspend fun getBasketProducts(
        @Query("stockId") stockId: String
    ): List<BasketProductDTO>

    @DELETE("basket/clear")
    @RequestWithAuthToken
    suspend fun clearBasket()

    @POST("basket/search")
    @RequestWithAuthToken
    suspend fun searchProducts(
        @Body filter: SearchProductsRequestDTO
    )
}