package com.grappim.network.api

import com.grappim.network.model.products.ProductDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface BasketApi {

    @POST("basket/add")
    suspend fun addProductToBasket(
        @Body request: ProductDTO
    )
}