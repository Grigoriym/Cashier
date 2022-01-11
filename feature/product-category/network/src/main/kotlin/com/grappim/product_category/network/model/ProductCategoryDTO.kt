package com.grappim.product_category.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String
)