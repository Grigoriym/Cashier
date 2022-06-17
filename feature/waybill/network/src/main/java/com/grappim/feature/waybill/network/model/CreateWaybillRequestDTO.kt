package com.grappim.feature.waybill.network.model

import com.grappim.feature.waybill.domain.model.WaybillStatus
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
    val status: com.grappim.feature.waybill.domain.model.WaybillStatus,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("type")
    val type: String
)