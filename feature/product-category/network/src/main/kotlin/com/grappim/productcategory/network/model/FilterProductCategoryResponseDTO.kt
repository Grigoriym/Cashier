package com.grappim.productcategory.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductCategoryResponseDTO(
    @SerialName("categories")
    val categories: List<ProductCategoryDTO>
)
