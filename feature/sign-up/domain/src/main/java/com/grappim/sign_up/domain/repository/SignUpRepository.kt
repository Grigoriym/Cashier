package com.grappim.sign_up.domain.repository

import com.grappim.common.lce.VoidTry
import com.grappim.sign_up.domain.interactor.sign_up.SignUpParams

interface SignUpRepository {

    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable>
}