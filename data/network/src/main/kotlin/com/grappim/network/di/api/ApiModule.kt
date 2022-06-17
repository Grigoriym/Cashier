package com.grappim.network.di.api

import com.grappim.common.di.ApiScope
import com.grappim.network.api.*
import com.grappim.product_category.network.api.ProductCategoryApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ApiModule {

    @[Provides ApiScope QualifierCashierApi]
    fun providerCashierApi(
        retrofit: Retrofit
    ): CashierApi = retrofit.create(CashierApi::class.java)


    @[Provides ApiScope QualifierProductCategoryApi]
    fun provideProductCategoryApi(
        retrofit: Retrofit
    ): ProductCategoryApi = retrofit.create(ProductCategoryApi::class.java)

    @[Provides ApiScope QualifierFeatureToggleApi]
    fun provideFeatureToggleApi(
        retrofit: Retrofit
    ): FeatureToggleApi = retrofit.create(FeatureToggleApi::class.java)

}