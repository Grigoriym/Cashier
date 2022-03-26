package com.grappim.auth.di

import com.grappim.auth.biometric.BiometricPromptUtils
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.router.ActivityRouter
import com.grappim.domain.repository.AuthRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.screens.CashierScreens
import com.grappim.workers.WorkerHelper

interface AuthDeps : ComponentDeps {

    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository
    fun authRepository(): AuthRepository

    fun appRouter(): ActivityRouter
    fun cashierScreens(): CashierScreens
    fun generalStorage(): GeneralStorage
    fun biometricPromptUtils(): BiometricPromptUtils
}