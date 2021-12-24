package com.grappim.network.di

import com.grappim.di.AppScope
import com.grappim.network.api.AuthApi
import com.grappim.network.api.CashierApi
import com.grappim.network.api.WaybillApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Qualifier

@Qualifier
annotation class QualifierCashierApi

@Qualifier
annotation class QualifierWaybillApi

@Qualifier
annotation class QualifierAuthApi

@Module
class ApiModule {

    @[Provides AppScope QualifierCashierApi]
    fun providerCashierApi(
        retrofit: Retrofit
    ): CashierApi = retrofit.create(CashierApi::class.java)

    @[Provides AppScope QualifierWaybillApi]
    fun provideWaybillApi(
        retrofit: Retrofit
    ): WaybillApi = retrofit.create(WaybillApi::class.java)

    @[Provides AppScope QualifierAuthApi]
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

}