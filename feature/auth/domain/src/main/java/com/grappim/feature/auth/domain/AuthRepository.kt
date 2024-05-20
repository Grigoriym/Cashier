package com.grappim.feature.auth.domain

import com.grappim.cashier.common.lce.Try

interface AuthRepository {
    suspend fun login(loginRequestData: LoginParams): Try<Unit, Throwable>
}
