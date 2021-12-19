package com.grappim.network.api

import com.grappim.network.model.login.LoginRequestDTO
import com.grappim.network.model.login.LoginResponseDTO
import com.grappim.network.model.login.SendTokenToRefreshRequestDTO
import com.grappim.network.model.sign_up.SignUpDTO
import com.grappim.network.model.sign_up.SignUpResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("merch/refresh")
    suspend fun refreshToken(
        @Body request: SendTokenToRefreshRequestDTO
    ): SendTokenToRefreshRequestDTO

    @POST("merch/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDTO
    ): LoginResponseDTO

    @POST("merch")
    suspend fun signUp(
        @Body signUpDTO: SignUpDTO
    ): SignUpResponseDTO

}