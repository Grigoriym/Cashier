package com.grappim.cashier.domain.repository

import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.domain.login.LoginUseCase
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(loginRequestData: LoginUseCase.LoginRequestData): Flow<Resource<Unit>>

}