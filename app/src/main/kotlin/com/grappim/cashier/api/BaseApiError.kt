package com.grappim.cashier.api

import com.google.gson.annotations.SerializedName

data class BaseApiError(
    val system: String?,
    val status: String?,
    @SerializedName("status_code")
    val statusCode: String,
    val message: String,
    val developerMessage: String?
)