package com.grappim.network.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWaybillProductRequestDTO(
    @SerialName("product")
    val product: PartialWaybillProductDTO
)
