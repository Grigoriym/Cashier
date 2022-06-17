package com.grappim.feature.bag.network.di

import com.grappim.common.di.ApiScope
import com.grappim.feature.bag.network.api.BasketApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object BagApiModule {
    @[Provides ApiScope QualifierBasketApi]
    fun provideBasketApi(
        retrofit: Retrofit
    ): BasketApi = retrofit.create(BasketApi::class.java)

}