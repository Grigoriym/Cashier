package com.grappim.feature.waybill.network.di

import com.grappim.cashier.common.di.ApiScope
import com.grappim.feature.waybill.network.api.WaybillApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object WaybillApiModule {

    @[Provides ApiScope QualifierWaybillApi]
    fun provideWaybillApi(retrofit: Retrofit): WaybillApi = retrofit.create(WaybillApi::class.java)
}
