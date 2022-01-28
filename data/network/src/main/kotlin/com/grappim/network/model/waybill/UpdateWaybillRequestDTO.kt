package com.grappim.network.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateWaybillRequestDTO(
    @SerialName("waybill")
    val waybill: WaybillDTO
)
