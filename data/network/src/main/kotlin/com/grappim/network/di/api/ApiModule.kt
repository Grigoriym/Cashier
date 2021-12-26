package com.grappim.network.di.api

import com.grappim.di.ApiScope
import com.grappim.network.api.AuthApi
import com.grappim.network.api.CashierApi
import com.grappim.network.api.WaybillApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @[Provides ApiScope QualifierCashierApi]
    fun providerCashierApi(
        retrofit: Retrofit
    ): CashierApi = retrofit.create(CashierApi::class.java)

    @[Provides ApiScope QualifierWaybillApi]
    fun provideWaybillApi(
        retrofit: Retrofit
    ): WaybillApi = retrofit.create(WaybillApi::class.java)

    @[Provides ApiScope QualifierAuthApi]
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

}