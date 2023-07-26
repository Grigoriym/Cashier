package com.grappim.feature.auth.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendTokenToRefreshRequestDTO(
    @SerialName("token")
    val token: String
)