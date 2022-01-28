package com.grappim.network.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWaybillProductResponseDTO(
    @SerialName("product")
    val product: WaybillProductDTO,
    @SerialName("found")
    val found: Boolean
)