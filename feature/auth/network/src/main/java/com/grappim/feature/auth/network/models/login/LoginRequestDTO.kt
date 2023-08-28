package com.grappim.feature.auth.network.models.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    @SerialName("mobile")
    val mobile: String,
    @SerialName("password")
    val password: String
)
