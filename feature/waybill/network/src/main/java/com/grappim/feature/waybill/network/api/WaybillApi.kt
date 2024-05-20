package com.grappim.feature.waybill.network.api

import com.grappim.cashier.common.annotations.RequestWithAuthToken
import com.grappim.feature.waybill.network.model.CreateWaybillProductRequestDTO
import com.grappim.feature.waybill.network.model.CreateWaybillProductResponseDTO
import com.grappim.feature.waybill.network.model.CreateWaybillRequestDTO
import com.grappim.feature.waybill.network.model.CreateWaybillResponseDTO
import com.grappim.feature.waybill.network.model.FilterWaybillsRequestDTO
import com.grappim.feature.waybill.network.model.FilterWaybillsResponseDTO
import com.grappim.feature.waybill.network.model.GetWaybillByBarcodeRequestDTO
import com.grappim.feature.waybill.network.model.GetWaybillByIdResponseDTO
import com.grappim.feature.waybill.network.model.GetWaybillProductResponseDTO
import com.grappim.feature.waybill.network.model.UpdateWaybillRequestDTO
import com.grappim.feature.waybill.network.model.WaybillProductsRequestDTO
import com.grappim.feature.waybill.network.model.WaybillProductsResponseDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WaybillApi {

    @POST("waybill/create")
    @RequestWithAuthToken
    suspend fun createWaybill(
        @Body createWaybillRequestDTO: CreateWaybillRequestDTO
    ): CreateWaybillResponseDTO

    @GET("waybill/conduct/{waybillId}")
    @RequestWithAuthToken
    suspend fun conductWaybill(@Path("waybillId") waybillId: Long)

    @GET("waybill/rollback/{waybillId}")
    @RequestWithAuthToken
    suspend fun rollbackWaybill(@Path("waybillId") waybillId: Long)

    @DELETE("waybill/{waybillId}")
    @RequestWithAuthToken
    suspend fun deleteWaybill(@Path("waybillId") waybillId: Long)

    @PUT("waybill/update")
    @RequestWithAuthToken
    suspend fun updateWaybill(@Body waybill: UpdateWaybillRequestDTO): GetWaybillByIdResponseDTO

    @POST("waybill/filter")
    @RequestWithAuthToken
    suspend fun filterWaybills(
        @Body filterWaybillsRequestDTO: FilterWaybillsRequestDTO
    ): FilterWaybillsResponseDTO

    @POST("waybill/product")
    @RequestWithAuthToken
    suspend fun createWaybillProduct(
        @Body createWaybillProductRequestDTO: CreateWaybillProductRequestDTO
    ): CreateWaybillProductResponseDTO

    @PUT("waybill/product")
    @RequestWithAuthToken
    suspend fun updateWaybillProduct(
        @Body createWaybillProductRequestDTO: CreateWaybillProductRequestDTO
    ): CreateWaybillProductResponseDTO

    @DELETE("waybill/product/{productId}")
    @RequestWithAuthToken
    suspend fun deleteWaybillProduct()

    @POST("waybill/product/filter")
    @RequestWithAuthToken
    suspend fun getWaybillProductList(
        @Body waybillProductsRequestDTO: WaybillProductsRequestDTO
    ): WaybillProductsResponseDTO

    @GET("waybill/{waybillId}")
    @RequestWithAuthToken
    suspend fun getWaybillById(@Path("waybillId") waybillId: Int): GetWaybillByIdResponseDTO

    @GET("waybill/product/{productId}")
    @RequestWithAuthToken
    suspend fun getWaybillProductById(@Path("productId") productId: Int)

    @POST("waybill/product/get")
    @RequestWithAuthToken
    suspend fun getWaybillProductByBarcode(
        @Body getWaybillByBarcodeRequestDTO: GetWaybillByBarcodeRequestDTO
    ): GetWaybillProductResponseDTO
}
