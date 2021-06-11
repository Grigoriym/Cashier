package com.grappim.cashier.data.remote.model.cashbox

import com.google.gson.annotations.SerializedName

data class GetCashBoxListRequestDTO(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String
)