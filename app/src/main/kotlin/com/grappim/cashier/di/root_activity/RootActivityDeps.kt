package com.grappim.cashier.di.root_activity

import android.content.Context
import com.grappim.core.utils.ResourceManager
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.*
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.workers.WorkerHelper

interface RootActivityDeps : ComponentDeps {

    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository
    fun authRepository(): AuthRepository

    fun resourceManager(): ResourceManager
    fun signUpRepository(): SignUpRepository

    fun generalStorage(): GeneralStorage

    fun productsRepository(): ProductsRepository
    fun paymentRepository():PaymentRepository

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository

    fun productCategoryRepository(): ProductCategoryRepository

    @ApplicationContext
    fun appContext(): Context
}
