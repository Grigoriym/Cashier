package com.grappim.feature.waybill.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWaybillProductRequestDTO(
    @SerialName("product")
    val product: PartialWaybillProductDTO
)
