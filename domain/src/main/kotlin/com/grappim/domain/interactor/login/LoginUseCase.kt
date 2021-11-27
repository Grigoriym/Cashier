package com.grappim.domain.interactor.login

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Try
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<LoginUseCase.Params, Unit>(ioDispatcher) {

    data class Params(
        val phone: String,
        val password: String
    )

    override fun execute(params: Params): Flow<Try<Unit>> =
        authRepository.login(params)
}