package com.grappim.network.api

import com.grappim.cashier.common.annotations.RequestWithAuthToken
import com.grappim.network.model.cashbox.GetCashBoxListRequestDTO
import com.grappim.network.model.cashbox.GetCashBoxListResponseDTO
import com.grappim.network.model.payment.CreateOrderRequestDTO
import com.grappim.network.model.payment.CreateOrderResponseDTO
import com.grappim.network.model.stock.GetStocksResponseDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CashierApi {

    @GET("stocks/list/{merchantId}")
    @RequestWithAuthToken
    suspend fun getStocks(@Path("merchantId") merchantId: String): GetStocksResponseDTO

    @POST("cashbox/list")
    @RequestWithAuthToken
    suspend fun getCashBoxList(
        @Body getCashBoxListRequestDTO: GetCashBoxListRequestDTO
    ): GetCashBoxListResponseDTO

    @POST("product/sync")
    @RequestWithAuthToken
    suspend fun productsSync()

    @POST("order/create")
    @RequestWithAuthToken
    suspend fun createOrder(
        @Body createOrderRequestDTO: CreateOrderRequestDTO
    ): CreateOrderResponseDTO
}
