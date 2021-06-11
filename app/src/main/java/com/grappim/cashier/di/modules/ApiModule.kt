package com.grappim.cashier.di.modules

import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.api.WaybillApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class QualifierCashierApi

@Qualifier
annotation class QualifierWaybillApi

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    @QualifierCashierApi
    fun providerCashierApi(
        retrofit: Retrofit
    ): CashierApi = retrofit.create(CashierApi::class.java)

    @Provides
    @Singleton
    @QualifierWaybillApi
    fun provideWaybillApi(
        retrofit: Retrofit
    ): WaybillApi = retrofit.create(WaybillApi::class.java)

}