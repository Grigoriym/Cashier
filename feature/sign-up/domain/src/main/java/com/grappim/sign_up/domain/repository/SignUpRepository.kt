package com.grappim.sign_up.domain.repository

import com.grappim.common.lce.Try
import com.grappim.sign_up.domain.interactor.SignUpUseCase
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun signUp(
        params: SignUpUseCase.Params
    ): Flow<Try<Unit>>
}