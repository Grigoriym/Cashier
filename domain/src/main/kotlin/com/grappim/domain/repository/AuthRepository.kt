package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.login.LoginParams

interface AuthRepository {

    suspend fun login(loginRequestData: LoginParams): Try<Unit, Throwable>

}