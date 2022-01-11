package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.login.LoginUseCase
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequestData: LoginUseCase.Params): Flow<Try<Unit>>

}