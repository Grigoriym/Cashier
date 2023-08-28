package com.grappim.productcategory.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateProductCategoryRequestDTO(
    @SerialName("category")
    val category: CreateProductCategoryDTO
)

@Serializable
data class CreateProductCategoryDTO(
    @SerialName("name")
    val name: String,
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("stockId")
    val stockId: String
)
