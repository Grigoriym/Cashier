package com.grappim.cashier.data.remote.model.cashbox

import com.google.gson.annotations.SerializedName

data class GetCashBoxListResponseDTO(
    @SerializedName("cash_boxes")
    val cashBoxes: List<CashBoxDTO>?
)