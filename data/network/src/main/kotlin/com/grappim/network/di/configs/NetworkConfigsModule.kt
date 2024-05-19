package com.grappim.network.di.configs

import com.grappim.cashier.network.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class NetworkConfigsModule {

    @Provides
    fun provideNetworkBuildConfig(): NetworkBuildConfigProvider =
        NetworkBuildConfigProvider(
            debug = BuildConfig.DEBUG
        )

    @Provides
    fun provideCashierApiProvider(): CashierApiUrlProvider =
        CashierApiUrlProvider(
            cashierApi = BuildConfig.CASHIER_API
        )
}
