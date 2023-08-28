package com.grappim.feature.auth.network.models.signup

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
