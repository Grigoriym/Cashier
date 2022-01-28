package com.grappim.network.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWaybillRequestDTO(
    @SerialName("waybill")
    val waybill: PartialWaybill
)

@Serializable
data class PartialWaybill(
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("status")
    val status: String,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("type")
    val type: String
)