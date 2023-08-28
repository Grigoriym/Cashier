package com.grappim.signup.domain.repository

import com.grappim.common.lce.VoidTry
import com.grappim.signup.domain.interactor.signup.SignUpParams

interface SignUpRepository {

    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable>
}
