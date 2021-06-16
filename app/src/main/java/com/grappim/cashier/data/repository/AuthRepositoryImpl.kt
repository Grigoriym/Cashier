package com.grappim.cashier.data.repository

import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.core.functional.map
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.BaseRepository
import com.grappim.cashier.data.remote.model.login.LoginRequestDTO
import com.grappim.cashier.di.modules.QualifierCashierApi
import com.grappim.cashier.domain.login.LoginUseCase
import com.grappim.cashier.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
) : BaseRepository(), AuthRepository {

    override suspend fun login(
        loginRequestData: LoginUseCase.LoginRequestData
    ): Either<Throwable, Unit> =
        apiCall {
            cashierApi.login(
                LoginRequestDTO(
                    mobile = loginRequestData.mobile,
                    password = loginRequestData.password
                )
            )
        }.map {
            generalStorage.setMerchantInfo(
                merchantId = it.merchantId,
                merchantName = it.merchantName
            )
            generalStorage.setAuthToken(it.token)
        }

}