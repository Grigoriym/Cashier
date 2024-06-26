package com.grappim.feature.auth.repository.remote

import com.grappim.cashier.common.async.di.IoDispatcher
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.common.lce.doOnError
import com.grappim.cashier.common.lce.doOnSuccess
import com.grappim.cashier.common.lce.mapSuccess
import com.grappim.cashier.common.lce.runOperationCatching
import com.grappim.cashier.data.repositoryapi.DataClearHelper
import com.grappim.domain.analytics.CrashesAnalytics
import com.grappim.domain.password.PasswordManager
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.auth.domain.AuthRepository
import com.grappim.feature.auth.domain.LoginParams
import com.grappim.feature.auth.network.api.AuthApi
import com.grappim.feature.auth.network.di.QualifierAuthApi
import com.grappim.feature.auth.network.models.login.LoginRequestDTO
import com.grappim.feature.auth.network.models.login.LoginResponseDTO
import com.grappim.logger.logE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@FeatureScope
class AuthRepositoryImpl @Inject constructor(
    @QualifierAuthApi private val authApi: AuthApi,
    private val generalStorage: GeneralStorage,
    private val dataClearHelper: DataClearHelper,
    private val crashesAnalytics: CrashesAnalytics,
    private val passwordManager: PasswordManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun login(loginRequestData: LoginParams): Try<Unit, Throwable> =
        runOperationCatching {
            val hashedPassword = passwordManager.encryptPassword(loginRequestData.password)
            authApi.login(
                LoginRequestDTO(
                    mobile = loginRequestData.phone,
                    password = hashedPassword
                )
            )
        }.doOnSuccess { response ->
            clearDataIfNotSameUser(response)
            saveData(response)
            setDataForCrashAnalytics(response)
        }.mapSuccess {}.doOnError { e ->
            logE(e)
        }

    private fun saveData(response: LoginResponseDTO) {
        generalStorage.setMerchantInfo(
            merchantId = response.merchantId,
            merchantName = response.merchantName
        )
        generalStorage.setAuthToken(response.token)
    }

    private suspend fun clearDataIfNotSameUser(response: LoginResponseDTO) =
        withContext(ioDispatcher) {
            val oldMerchantId = generalStorage.getMerchantIdNullable()
            if (oldMerchantId != response.merchantId) {
                generalStorage.clearData()
                dataClearHelper.clearDb()
            }
        }

    private fun setDataForCrashAnalytics(response: LoginResponseDTO) {
        crashesAnalytics.setUserId(response.merchantId)
        crashesAnalytics.userName(response.merchantName)
    }
}
