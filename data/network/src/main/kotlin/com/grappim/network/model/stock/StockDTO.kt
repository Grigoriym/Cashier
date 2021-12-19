package com.grappim.network.model.stock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockDTO(
    @SerialName("id")
    val id: String,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("name")
    val name: String
)