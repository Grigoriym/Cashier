package com.grappim.domain.repository

import com.grappim.domain.base.Try
import com.grappim.domain.interactor.sign_up.SignUpUseCase
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun signUp(
        params: SignUpUseCase.Params
    ): Flow<Try<Unit>>
}