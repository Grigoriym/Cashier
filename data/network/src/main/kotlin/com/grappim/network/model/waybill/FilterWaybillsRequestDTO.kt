package com.grappim.network.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterWaybillsRequestDTO(
    @SerialName("limit")
    val limit: Int,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("waybillType")
    val waybillType: String
)