package com.grappim.network.model.sign_up

import com.google.gson.annotations.SerializedName

data class SignUpDTO(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)