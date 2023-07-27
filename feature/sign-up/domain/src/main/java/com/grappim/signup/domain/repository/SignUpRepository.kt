package com.grappim.signup.domain.repository

import com.grappim.common.lce.VoidTry
import com.grappim.signup.domain.interactor.sign_up.SignUpParams

interface SignUpRepository {

    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable>
}
