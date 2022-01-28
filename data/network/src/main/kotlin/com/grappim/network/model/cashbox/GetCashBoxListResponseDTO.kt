package com.grappim.network.model.cashbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCashBoxListResponseDTO(
    @SerialName("cashBoxes")
    val cashBoxes: List<CashBoxDTO>
)