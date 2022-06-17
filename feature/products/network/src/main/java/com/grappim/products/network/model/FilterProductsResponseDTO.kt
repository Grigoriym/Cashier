package com.grappim.products.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductsResponseDTO(
    @SerialName("products")
    val products: List<ProductDTO>
)