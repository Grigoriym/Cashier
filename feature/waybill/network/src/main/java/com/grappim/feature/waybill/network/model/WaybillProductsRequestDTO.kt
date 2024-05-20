package com.grappim.feature.waybill.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WaybillProductsRequestDTO(
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("waybillId")
    val waybillId: Long
)
