package com.grappim.cashier.data.remote.model.waybill

import com.google.gson.annotations.SerializedName

data class CreateWaybillRequestDTO(
    @SerializedName("waybill")
    val waybill: PartialWaybill
)

data class PartialWaybill(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("type")
    val type: String
)