package com.grappim.cashier.domain.repository

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.domain.login.LoginUseCase

interface AuthRepository {

    suspend fun login(loginRequestData: LoginUseCase.LoginRequestData): Either<Throwable, Unit>

}