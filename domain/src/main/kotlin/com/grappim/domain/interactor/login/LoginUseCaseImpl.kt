package com.grappim.domain.interactor.login

import com.grappim.common.lce.Try
import com.grappim.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LoginUseCase {

    override suspend fun login(params: LoginParams): Try<Unit, Throwable> =
        authRepository.login(params)
}