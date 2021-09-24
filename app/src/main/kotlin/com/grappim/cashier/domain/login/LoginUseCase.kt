package com.grappim.cashier.domain.login

import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.platform.FlowUseCase
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<LoginUseCase.LoginRequestData, Unit>(ioDispatcher) {

    data class LoginRequestData(
        val mobile: String,
        val password: String
    )

    override fun execute(parameters: LoginRequestData): Flow<Resource<Unit>> =
        authRepository.login(parameters)
}