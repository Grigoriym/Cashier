package com.grappim.sign_up.domain.repository

import com.grappim.common.lce.VoidTry
import com.grappim.sign_up.domain.interactor.SignUpParams

interface SignUpRepository {

    suspend fun signUp(
        params: SignUpParams
    ): VoidTry<Throwable>
}