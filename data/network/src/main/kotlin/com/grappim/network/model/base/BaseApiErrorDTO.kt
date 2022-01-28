package com.grappim.network.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseApiErrorDTO(
    @SerialName("system")
    val system: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("statusCode")
    val statusCode: String,
    @SerialName("message")
    val message: String,
    @SerialName("developerMessage")
    val developerMessage: String?
)