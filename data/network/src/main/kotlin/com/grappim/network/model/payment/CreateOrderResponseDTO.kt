package com.grappim.network.model.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderResponseDTO(
    @SerialName("id")
    val id: Long
)
