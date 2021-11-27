package com.grappim.repository.remote

import com.grappim.network.api.CashierApi
import com.grappim.network.di.QualifierCashierApi
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.repository.AuthRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.model.login.LoginRequestDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
) : AuthRepository {

    override fun login(
        loginRequestData: LoginUseCase.Params
    ): Flow<Try<Unit>> =
        flow {
            emit(Try.Loading)
            val response = cashierApi.login(
                LoginRequestDTO(
                    mobile = loginRequestData.phone,
                    password = loginRequestData.password
                )
            )
            generalStorage.setMerchantInfo(
                merchantId = response.merchantId,
                merchantName = response.merchantName
            )
            generalStorage.setAuthToken(response.token)

            emit(Try.Success(Unit))
        }

}