package com.grappim.network.di.api

import com.grappim.common.di.ApiScope
import com.grappim.network.api.AuthApi
import com.grappim.network.api.CashierApi
import com.grappim.network.api.WaybillApi
import com.grappim.product_category.network.api.ProductCategoryApi
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

    @[Provides ApiScope QualifierProductCategoryApi]
    fun provideProductCategoryApi(
        retrofit: Retrofit
    ): ProductCategoryApi = retrofit.create(ProductCategoryApi::class.java)

}