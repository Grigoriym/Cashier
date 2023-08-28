package com.grappim.signup.domain.interactor.signup

import com.grappim.common.lce.VoidTry
import com.grappim.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {

    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable> =
        signUpRepository.signUp(params)
}
