package com.grappim.network.model.base

import com.google.gson.annotations.SerializedName

data class BaseApiErrorAm(
    val system: String?,
    val status: String?,
    @SerializedName("status_code")
    val statusCode: String,
    val message: String,
    val developerMessage: String?
)