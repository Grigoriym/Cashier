package com.grappim.feature.waybill.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WaybillProductsResponseDTO(
    @SerialName("products")
    val products: List<WaybillProductDTO>?
)
