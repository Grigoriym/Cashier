package com.grappim.network.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseFilterDTO(
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Long,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String
)
