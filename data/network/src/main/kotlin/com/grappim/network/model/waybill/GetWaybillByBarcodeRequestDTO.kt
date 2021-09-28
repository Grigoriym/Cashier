package com.grappim.network.model.waybill

import com.google.gson.annotations.SerializedName

data class GetWaybillByBarcodeRequestDTO(
    val barcode: String,
    @SerializedName("waybill_id")
    val waybillId: Int
)
