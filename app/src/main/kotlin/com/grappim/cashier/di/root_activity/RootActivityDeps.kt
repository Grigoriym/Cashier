package com.grappim.cashier.di.root_activity

import com.grappim.core.utils.ResourceManager
import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.AuthRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.SignUpRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.workers.WorkerHelper

interface RootActivityDeps : ComponentDeps {

    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository
    fun authRepository(): AuthRepository

    fun resourceManager(): ResourceManager
    fun signUpRepository(): SignUpRepository

    fun generalStorage(): GeneralStorage

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
}
