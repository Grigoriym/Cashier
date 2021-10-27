package com.grappim.network.model.login

data class LoginResponseDTO(
    val merchantId: String,
    val merchantName: String,
    val token: String
)