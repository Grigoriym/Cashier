package com.grappim.cashier.data.repository

import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.model.login.LoginRequestDTO
import com.grappim.cashier.di.modules.QualifierCashierApi
import com.grappim.cashier.domain.login.LoginUseCase
import com.grappim.cashier.domain.repository.AuthRepository
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
        loginRequestData: LoginUseCase.LoginRequestData
    ): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading)
            val response = cashierApi.login(
                LoginRequestDTO(
                    mobile = loginRequestData.mobile,
                    password = loginRequestData.password
                )
            )
            generalStorage.setMerchantInfo(
                merchantId = response.merchantId,
                merchantName = response.merchantName
            )
            generalStorage.setAuthToken(response.token)

            emit(Resource.Success(Unit))
        }

}