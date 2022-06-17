package com.grappim.feature.waybill.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWaybillByBarcodeRequestDTO(
    @SerialName("barcode")
    val barcode: String,
    @SerialName("waybillId")
    val waybillId: Long
)
