package com.grappim.products.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductsRequestDTO(
    @SerialName("offset")
    val offset: Long,
    @SerialName("limit")
    val limit: Long,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String,
    @SerialName("query")
    val query: String? = null
)