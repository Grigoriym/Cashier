package com.grappim.network.api

import com.grappim.network.interceptors.RequestWithAuthToken
import com.grappim.network.model.waybill.*
import retrofit2.http.*

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