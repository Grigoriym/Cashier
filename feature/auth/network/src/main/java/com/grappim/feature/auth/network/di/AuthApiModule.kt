package com.grappim.feature.auth.network.di

import com.grappim.cashier.common.di.ApiScope
import com.grappim.feature.auth.network.api.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthApiModule {

    @[Provides ApiScope QualifierAuthApi]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}
