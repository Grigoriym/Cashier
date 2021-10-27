package com.grappim.network.model.cashbox

import com.google.gson.annotations.SerializedName

data class GetCashBoxListRequestDTO(
    @SerializedName("merchantId")
    val merchantId: String,
    @SerializedName("stockId")
    val stockId: String
)