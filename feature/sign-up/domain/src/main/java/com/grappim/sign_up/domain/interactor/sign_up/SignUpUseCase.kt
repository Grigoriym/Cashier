package com.grappim.sign_up.domain.interactor.sign_up

import com.grappim.common.lce.VoidTry
import com.grappim.sign_up.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {

    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable> =
        signUpRepository.signUp(params)
}