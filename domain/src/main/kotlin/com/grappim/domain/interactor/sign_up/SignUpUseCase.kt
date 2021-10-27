package com.grappim.domain.interactor.sign_up

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Result
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.repository.SignUpRepository
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

    override fun execute(params: Params): Flow<Result<Unit>> =
        signUpRepository.signUp(params)
}