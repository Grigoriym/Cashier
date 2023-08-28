package com.grappim.network.model.cashbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCashBoxListRequestDTO(
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String
)
