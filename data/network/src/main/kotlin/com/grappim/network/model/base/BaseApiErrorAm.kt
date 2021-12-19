package com.grappim.network.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseApiErrorAm(
    val system: String?,
    val status: String?,
    @SerialName("statusCode")
    val statusCode: String,
    val message: String,
    val developerMessage: String?
)