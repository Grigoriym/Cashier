package com.grappim.repository.di

import com.grappim.domain.repository.FeatureToggleRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.feature.bag.domain.BagRepository
import com.grappim.feature.bag.repository.BagRepositoryImpl
import com.grappim.feature.payment_method.domain.repository.PaymentRepository
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.product_category.repository.ProductCategoryRepositoryImpl
import com.grappim.repository.local.FeatureToggleLocalRepositoryImpl
import com.grappim.repository.local.SelectCashBoxLocalRepositoryImpl
import com.grappim.repository.local.SelectStockLocalRepositoryImpl
import com.grappim.repository.remote.FeatureToggleRepositoryImpl
import com.grappim.repository.remote.GeneralRepositoryImpl
import com.grappim.repository.remote.PaymentRepositoryImpl
import com.grappim.repository.remote.SelectInfoRemoteRepositoryImpl
import com.grappim.repository.utils.DataClearHelper
import com.grappim.repository.utils.DataClearHelperImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindDataClearHelper(
        dataClearHelperImpl: DataClearHelperImpl
    ): DataClearHelper

    @Binds
    fun bindGeneralRepository(
        generalRepositoryImpl: GeneralRepositoryImpl
    ): GeneralRepository

    @Binds
    fun bindSelectInfoRepository(
        selectInfoRepositoryImpl: SelectInfoRemoteRepositoryImpl
    ): SelectInfoRemoteRepository

    @Binds
    fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository

    @Binds
    fun bindSelectStockRepository(
        selectStockLocalRepositoryImpl: SelectStockLocalRepositoryImpl
    ): SelectStockLocalRepository

    @Binds
    fun bindSelectCashBoxRepository(
        selectCashBoxLocalRepositoryImpl: SelectCashBoxLocalRepositoryImpl
    ): SelectCashBoxLocalRepository

    @Binds
    fun bindProductCategoryRepository(
        productCategoryRepositoryImpl: ProductCategoryRepositoryImpl
    ): ProductCategoryRepository

    @Binds
    fun bindFeatureToggleRepository(
        featureToggleRepositoryImpl: FeatureToggleRepositoryImpl
    ): FeatureToggleRepository

    @Binds
    fun bindFeatureToggleLocalRepository(
        featureToggleLocalRepositoryImpl: FeatureToggleLocalRepositoryImpl
    ): FeatureToggleLocalRepository
}