package com.grappim.feature.auth.domain

import com.grappim.common.lce.Try
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun login(params: LoginParams): Try<Unit, Throwable> =
        authRepository.login(params)
}