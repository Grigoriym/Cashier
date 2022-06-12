package com.grappim.domain.interactor.login

import com.grappim.common.lce.Try

interface LoginUseCase {

    suspend fun login(params: LoginParams): Try<Unit, Throwable>

}