package com.grappim.feature.waybill.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWaybillResponseDTO(
    @SerialName("waybill")
    val waybill: WaybillDTO
)
