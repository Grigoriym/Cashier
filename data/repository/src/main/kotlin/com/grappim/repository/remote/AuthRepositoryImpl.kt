package com.grappim.repository.remote

import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.domain.analytics.CrashesAnalytics
import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.password.PasswordManager
import com.grappim.domain.repository.AuthRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.AuthApi
import com.grappim.network.di.api.QualifierAuthApi
import com.grappim.network.model.login.LoginRequestDTO
import com.grappim.repository.utils.DataClearHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@AppScope
class AuthRepositoryImpl @Inject constructor(
    @QualifierAuthApi private val authApi: AuthApi,
    private val generalStorage: GeneralStorage,
    private val dataClearHelper: DataClearHelper,
    private val crashesAnalytics: CrashesAnalytics,
    private val passwordManager: PasswordManager
) : AuthRepository {

    override fun login(
        loginRequestData: LoginUseCase.Params
    ): Flow<Try<Unit>> =
        flow {
            emit(Try.Loading)
            val hashedPassword = passwordManager.encryptPassword(loginRequestData.password)
            val response = authApi.login(
                LoginRequestDTO(
                    mobile = loginRequestData.phone,
                    password = hashedPassword
                )
            )
            val oldMerchantId = generalStorage.getMerchantIdNullable()
            if (oldMerchantId != response.merchantId) {
                generalStorage.clearData()
                dataClearHelper.clearDb()
            }

            generalStorage.setMerchantInfo(
                merchantId = response.merchantId,
                merchantName = response.merchantName
            )
            generalStorage.setAuthToken(response.token)

            crashesAnalytics.setUserId(response.merchantId)
            crashesAnalytics.userName(response.merchantName)

            emit(Try.Success(Unit))
        }

}