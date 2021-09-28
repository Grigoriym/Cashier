package com.grappim.domain.repository

import com.grappim.domain.base.Result
import com.grappim.domain.interactor.login.LoginUseCase
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequestData: LoginUseCase.Params): Flow<Result<Unit>>

}