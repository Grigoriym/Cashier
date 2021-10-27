package com.grappim.network.model.stock

import com.google.gson.annotations.SerializedName

data class StockDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("merchantId")
    val merchantId: String,
    @SerializedName("name")
    val name: String
)