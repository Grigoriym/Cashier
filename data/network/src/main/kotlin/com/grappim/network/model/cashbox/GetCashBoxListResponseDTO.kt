package com.grappim.network.model.cashbox

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetCashBoxListResponseDTO(
    @SerializedName("cashBoxes")
    val cashBoxes: List<CashBoxDTO>
)