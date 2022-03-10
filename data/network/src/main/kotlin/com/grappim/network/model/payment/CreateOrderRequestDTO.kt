package com.grappim.network.model.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequestDTO(
    @SerialName("order")
    val order: CreateOrderDTO
)
