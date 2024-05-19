package com.grappim.feature.auth.presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.analytics.CrashesAnalytics
import com.grappim.domain.password.PasswordManager
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.auth.network.api.AuthApi
import com.grappim.feature.auth.network.di.QualifierAuthApi
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import com.grappim.cashier.data.repositoryapi.DataClearHelper
import com.grappim.utils.biometric.BiometricPromptUtils
import com.grappim.workers.WorkerHelperImpl
import retrofit2.Retrofit

interface AuthDeps : ComponentDeps {

    fun workerHelper(): WorkerHelperImpl
    fun generalRepository(): GeneralRepository

    fun appRouter(): ActivityRouter
    fun cashierScreens(): CashierScreens
    fun generalStorage(): GeneralStorage
    fun biometricPromptUtils(): BiometricPromptUtils

    fun dataClearHelper(): DataClearHelper
    fun crashesAnalytics(): CrashesAnalytics
    fun passwordManager(): PasswordManager

    fun retrofit(): Retrofit

    @QualifierAuthApi
    fun authApi(): AuthApi
}
