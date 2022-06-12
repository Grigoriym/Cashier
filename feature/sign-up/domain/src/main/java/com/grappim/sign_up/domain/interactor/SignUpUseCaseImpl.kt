package com.grappim.sign_up.domain.interactor

import com.grappim.common.lce.VoidTry
import com.grappim.sign_up.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val signUpRepository: SignUpRepository
) : SignUpUseCase {

    override suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable> =
        signUpRepository.signUp(params)
}