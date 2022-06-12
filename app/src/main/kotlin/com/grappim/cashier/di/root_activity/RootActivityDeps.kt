package com.grappim.cashier.di.root_activity

import android.content.Context
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.core.utils.ResourceManager
import com.grappim.date_time.DateTimeIsoInstant
import com.grappim.domain.interactor.basket.GetBasketItemsUseCase
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.sales.SubtractProductFromBasketUseCase
import com.grappim.domain.password.PasswordManager
import com.grappim.domain.repository.*
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.AuthApi
import com.grappim.network.di.api.QualifierAuthApi
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.workers.WorkerHelper
import java.time.format.DateTimeFormatter

interface RootActivityDeps : ComponentDeps {

    fun workerHelper(): WorkerHelper
    fun generalRepository(): GeneralRepository
    fun authRepository(): AuthRepository

    fun resourceManager(): ResourceManager

    fun generalStorage(): GeneralStorage

    fun productsRepository(): ProductsRepository
    fun paymentRepository(): PaymentRepository
    fun basketRepository(): BasketRepository

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository

    fun productCategoryRepository(): ProductCategoryRepository

    fun featureToggleLocalRepository(): FeatureToggleLocalRepository

    @ApplicationContext
    fun appContext(): Context

    @QualifierAuthApi
    fun authApi(): AuthApi

    @DateTimeIsoInstant
    fun dtfIso(): DateTimeFormatter
    fun passwordManager(): PasswordManager

    fun getBasketItemsUseCase(): GetBasketItemsUseCase
    fun searchProductsUseCase(): SearchProductsUseCase
    fun addProductToBasketUseCase(): AddProductToBasketUseCase
    fun subtractProductFromBasketUseCase(): SubtractProductFromBasketUseCase
    fun getProductByBarcodeUseCase(): GetProductByBarcodeUseCase
    fun getCategoryListUseCase(): GetCategoryListUseCase
}
