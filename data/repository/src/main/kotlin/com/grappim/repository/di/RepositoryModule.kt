package com.grappim.repository.di

import com.grappim.domain.repository.FeatureToggleRepository
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.feature.paymentmethod.domain.repository.PaymentRepository
import com.grappim.productcategory.domain.repository.ProductCategoryRepository
import com.grappim.productcategory.repository.ProductCategoryRepositoryImpl
import com.grappim.repository.local.FeatureToggleLocalRepositoryImpl
import com.grappim.repository.local.SelectCashBoxLocalRepositoryImpl
import com.grappim.repository.local.SelectStockLocalRepositoryImpl
import com.grappim.repository.remote.FeatureToggleRepositoryImpl
import com.grappim.repository.remote.GeneralRepositoryImpl
import com.grappim.repository.remote.PaymentRepositoryImpl
import com.grappim.repository.remote.SelectInfoRemoteRepositoryImpl
import com.grappim.cashier.data.repositoryapi.DataClearHelper
import com.grappim.repository.utils.DataClearHelperImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindDataClearHelper(
        dataClearHelperImpl: DataClearHelperImpl
    ): com.grappim.cashier.data.repositoryapi.DataClearHelper

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
