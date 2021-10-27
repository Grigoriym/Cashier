package com.grappim.network.api

import com.grappim.network.model.sign_up.SignUpDTO
import com.grappim.network.model.sign_up.SignUpResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("user")
    suspend fun signUp(
        @Body signUpDTO: SignUpDTO
    ): SignUpResponseDTO
}