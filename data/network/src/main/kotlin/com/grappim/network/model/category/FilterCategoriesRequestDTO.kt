package com.grappim.network.model.category

import com.google.gson.annotations.SerializedName

data class FilterCategoriesRequestDTO(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String,
    val offset: Long,
    val limit: Long
)
