package com.grappim.network.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendTokenToRefreshRequestDTO(
    @SerialName("token")
    val token: String
)