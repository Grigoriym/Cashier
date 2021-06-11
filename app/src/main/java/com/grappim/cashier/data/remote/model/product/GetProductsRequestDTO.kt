package com.grappim.cashier.data.remote.model.product

import com.google.gson.annotations.SerializedName

data class GetProductsRequestDTO(
    val offset: Long,
    val limit: Long,
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("stock_id")
    val stockId: String,
    val name: String? = null
)