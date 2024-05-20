package com.grappim.feature.bag.network.api

import com.grappim.cashier.common.annotations.RequestWithAuthToken
import com.grappim.feature.bag.network.model.AddBasketProductDTO
import com.grappim.feature.bag.network.model.BasketProductDTO
import com.grappim.feature.bag.network.model.SearchProductsRequestDTO
import com.grappim.feature.bag.network.model.SubtractBasketProductResponseDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BasketApi {

    @POST("basket/add")
    @RequestWithAuthToken
    suspend fun addProductToBasket(@Body request: AddBasketProductDTO): BasketProductDTO

    @POST("basket/subtract")
    @RequestWithAuthToken
    suspend fun subtractProduct(@Body request: BasketProductDTO): SubtractBasketProductResponseDTO

    @POST("basket/remove")
    @RequestWithAuthToken
    suspend fun removeProduct(@Body request: BasketProductDTO)

    @GET("basket")
    @RequestWithAuthToken
    suspend fun getBasketProducts(@Query("stockId") stockId: String): List<BasketProductDTO>

    @DELETE("basket/clear")
    @RequestWithAuthToken
    suspend fun clearBasket()

    @POST("basket/search")
    @RequestWithAuthToken
    suspend fun searchProducts(@Body filter: SearchProductsRequestDTO)
}
