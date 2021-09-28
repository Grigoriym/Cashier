package com.grappim.network.model.category

import com.google.gson.annotations.SerializedName

data class CreateCategoryRequestDTO(
    val category: CreateCategoryDTO
)

data class CreateCategoryDTO(
    @SerializedName("merchant_id")
    val merchantId: String,
    val name: String,
    @SerializedName("stock_id")
    val stockId: String
)