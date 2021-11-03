package com.grappim.network.model.cashbox

import com.google.gson.annotations.SerializedName

data class CashBoxDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("cash_box_id")
    val cashBoxId: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String
)