package com.grappim.sign_up.domain.interactor

import com.grappim.common.lce.VoidTry

interface SignUpUseCase {
    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable>
}