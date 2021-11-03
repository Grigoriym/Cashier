package com.grappim.network.model.stock

import com.google.gson.annotations.SerializedName

data class StockDTO(
    @SerializedName("stock_id")
    val id: String,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_name")
    val name: String
)