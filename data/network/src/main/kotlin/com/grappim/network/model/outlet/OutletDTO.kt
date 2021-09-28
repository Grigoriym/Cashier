package com.grappim.network.model.outlet

import com.google.gson.annotations.SerializedName

data class OutletDTO(
    @SerializedName("stock_id")
    val stockId: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_name")
    val stockName: String
)