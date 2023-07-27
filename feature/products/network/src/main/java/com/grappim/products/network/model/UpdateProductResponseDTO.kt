package com.grappim.products.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProductResponseDTO(
    @SerialName("product")
    val product: ProductDTO
)
