package com.grappim.feature.auth.network.api

import com.grappim.feature.auth.network.models.SendTokenToRefreshRequestDTO
import com.grappim.feature.auth.network.models.login.LoginRequestDTO
import com.grappim.feature.auth.network.models.login.LoginResponseDTO
import com.grappim.feature.auth.network.models.signup.SignUpDTO
import com.grappim.feature.auth.network.models.signup.SignUpResponseDTO
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
