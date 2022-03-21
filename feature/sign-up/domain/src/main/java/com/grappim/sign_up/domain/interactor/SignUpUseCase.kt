package com.grappim.sign_up.domain.interactor

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.sign_up.domain.repository.SignUpRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<SignUpUseCase.Params, Unit>(ioDispatcher) {

    data class Params(
        val phone: String,
        val email: String,
        val password: String
    )

    override fun execute(params: Params): Flow<Try<Unit>> =
        signUpRepository.signUp(params)
}