package com.grappim.network.model.cashbox

import com.google.gson.annotations.SerializedName

data class CashBoxDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("cashBoxId")
    val cashBoxId: String,
    @SerializedName("merchantId")
    val merchantId: String,
    @SerializedName("stockId")
    val stockId: String
)