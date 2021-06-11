package com.grappim.cashier.di.modules

import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.executor.CoroutineContextProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractApplicationModule {

    @Binds
    abstract fun provideCoroutineContextProvider(
        coroutineContextProviderImpl: CoroutineContextProviderImpl
    ): CoroutineContextProvider
}