package com.grappim.cashier.api

import com.grappim.cashier.core.network.RequestWithAuthToken
import com.grappim.cashier.data.remote.model.waybill.CreateWaybillProductRequestDTO
import com.grappim.cashier.data.remote.model.waybill.CreateWaybillProductResponseDTO
import com.grappim.cashier.data.remote.model.waybill.CreateWaybillRequestDTO
import com.grappim.cashier.data.remote.model.waybill.CreateWaybillResponseDTO
import com.grappim.cashier.data.remote.model.waybill.FilterWaybillsRequestDTO
import com.grappim.cashier.data.remote.model.waybill.FilterWaybillsResponseDTO
import com.grappim.cashier.data.remote.model.waybill.GetWaybillByBarcodeRequestDTO
import com.grappim.cashier.data.remote.model.waybill.GetWaybillByIdResponseDTO
import com.grappim.cashier.data.remote.model.waybill.GetWaybillProductResponseDTO
import com.grappim.cashier.data.remote.model.waybill.WaybillProductsRequestDTO
import com.grappim.cashier.data.remote.model.waybill.WaybillProductsResponseDTO
import com.grappim.cashier.domain.waybill.UpdateWaybillRequestDTO
import com.grappim.cashier.domain.waybill.Waybill
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
    suspend fun conductWaybill(
        @Path("waybillId") waybillId: Int
    ): GetWaybillByIdResponseDTO

    @GET("waybill/rollback/{waybillId}")
    @RequestWithAuthToken
    suspend fun rollbackWaybill(
        @Path("waybillId") waybillId: Int
    ): GetWaybillByIdResponseDTO

    @DELETE("waybill/{waybillId}")
    @RequestWithAuthToken
    suspend fun deleteWaybill(
        @Path("waybillId") waybillId: String
    )

    @PUT("waybill/update")
    @RequestWithAuthToken
    suspend fun updateWaybill(
        @Body waybill: UpdateWaybillRequestDTO
    ): GetWaybillByIdResponseDTO

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

    @POST("waybill/product/get_list")
    @RequestWithAuthToken
    suspend fun getWaybillProductList(
        @Body waybillProductsRequestDTO: WaybillProductsRequestDTO
    ): WaybillProductsResponseDTO

    @GET("waybill/{waybillId}")
    @RequestWithAuthToken
    suspend fun getWaybillById(
        @Path("waybillId") waybillId: Int
    ): GetWaybillByIdResponseDTO

    @GET("waybill/product/{productId}")
    @RequestWithAuthToken
    suspend fun getWaybillProductById(
        @Path("productId") productId: Int
    )

    @POST("waybill/product/get")
    @RequestWithAuthToken
    suspend fun getWaybillProductByBarcode(
        @Body getWaybillByBarcodeRequestDTO: GetWaybillByBarcodeRequestDTO
    ): GetWaybillProductResponseDTO
}