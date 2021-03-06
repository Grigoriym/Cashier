package com.grappim.feature.auth.data_network.api

import com.grappim.feature.auth.data_network.models.SendTokenToRefreshRequestDTO
import com.grappim.feature.auth.data_network.models.login.LoginRequestDTO
import com.grappim.feature.auth.data_network.models.login.LoginResponseDTO
import com.grappim.feature.auth.data_network.models.sign_up.SignUpDTO
import com.grappim.feature.auth.data_network.models.sign_up.SignUpResponseDTO
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