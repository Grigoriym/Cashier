package com.grappim.network.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWaybillByBarcodeRequestDTO(
    @SerialName("barcode")
    val barcode: String,
    @SerialName("waybillId")
    val waybillId: Int
)
