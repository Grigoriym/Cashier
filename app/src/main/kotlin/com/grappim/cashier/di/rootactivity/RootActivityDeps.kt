package com.grappim.cashier.di.rootactivity

import android.content.Context
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.date_time.DateTimeIsoInstant
import com.grappim.domain.analytics.CrashesAnalytics
import com.grappim.domain.password.PasswordManager
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.auth.network.api.AuthApi
import com.grappim.feature.auth.network.di.QualifierAuthApi
import com.grappim.feature.bag.domain.BagRepository
import com.grappim.feature.payment_method.domain.repository.PaymentRepository
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.repository.utils.DataClearHelper
import com.grappim.workers.WorkerHelper
import retrofit2.Retrofit
import java.time.format.DateTimeFormatter

interface RootActivityDeps : ComponentDeps {

    fun retrofit(): Retrofit

    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository

    fun generalStorage(): GeneralStorage

    fun productsRepository(): ProductsRepository
    fun paymentRepository(): PaymentRepository
    fun basketRepository(): BagRepository

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository

    fun productCategoryRepository(): ProductCategoryRepository

    fun featureToggleLocalRepository(): FeatureToggleLocalRepository

    @ApplicationContext
    fun appContext(): Context

    @DateTimeIsoInstant
    fun dtfIso(): DateTimeFormatter
    fun passwordManager(): PasswordManager

    fun dataClearHelper(): DataClearHelper
    fun crashesAnalytics(): CrashesAnalytics

    @QualifierAuthApi
    fun authApi(): AuthApi
}
