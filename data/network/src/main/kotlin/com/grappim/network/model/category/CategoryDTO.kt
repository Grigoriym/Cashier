package com.grappim.network.model.category

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String,
    val name: String,
    val id: Int
)
