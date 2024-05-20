package com.grappim.products.network.di

import com.grappim.cashier.common.di.ApiScope
import com.grappim.products.network.api.ProductsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ProductsApiModule {
    @[Provides ApiScope QualifierProductsApi]
    fun provideProductsApi(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)
}
