package com.grappim.network.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProductResponseDTO(
    @SerialName("product")
    val product: ProductDTO
)