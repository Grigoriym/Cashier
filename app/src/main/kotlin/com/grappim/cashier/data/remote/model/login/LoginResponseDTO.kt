package com.grappim.cashier.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponseDTO(
    @SerializedName("merchant_id")
    val merchantId: String,
    @SerializedName("merchant_name")
    val merchantName: String,
    val token: String
)