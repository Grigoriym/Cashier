package com.grappim.domain.interactor.login

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
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