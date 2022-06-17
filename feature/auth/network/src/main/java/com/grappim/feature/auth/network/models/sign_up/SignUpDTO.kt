package com.grappim.feature.auth.data_network.models.sign_up

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpDTO(
    @SerialName("phone")
    val phone: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)