package com.grappim.network.model.sign_up

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDTO(
    @SerialName("phone")
    val phone: String,
    @SerialName("email")
    val email: String
)