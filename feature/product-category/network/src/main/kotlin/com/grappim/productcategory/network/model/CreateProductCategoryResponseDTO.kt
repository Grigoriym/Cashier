package com.grappim.productcategory.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateProductCategoryResponseDTO(
    @SerialName("id")
    val id: Long
)
