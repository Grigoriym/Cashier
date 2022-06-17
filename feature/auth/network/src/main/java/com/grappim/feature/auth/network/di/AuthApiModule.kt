package com.grappim.feature.auth.data_network.di

import com.grappim.common.di.ApiScope
import com.grappim.feature.auth.data_network.api.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthApiModule {

    @[Provides ApiScope QualifierAuthApi]
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi = retrofit.create(AuthApi::class.java)

}