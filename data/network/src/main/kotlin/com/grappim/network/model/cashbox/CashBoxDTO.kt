package com.grappim.network.model.cashbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CashBoxDTO(
    @SerialName("name")
    val name: String,
    @SerialName("cashBoxId")
    val cashBoxId: String,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String
)