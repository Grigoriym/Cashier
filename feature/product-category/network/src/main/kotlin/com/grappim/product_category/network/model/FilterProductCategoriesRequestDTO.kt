package com.grappim.product_category.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductCategoriesRequestDTO(
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("offset")
    val offset: Long,
    @SerialName("limit")
    val limit: Long
)
