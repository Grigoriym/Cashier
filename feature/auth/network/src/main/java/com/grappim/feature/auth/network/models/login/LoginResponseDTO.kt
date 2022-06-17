package com.grappim.feature.auth.data_network.models.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    @SerialName("merchantId")
    val merchantId: String,
    @SerialName("merchantName")
    val merchantName: String,
    @SerialName("token")
    val token: String
)