package com.grappim.cashier.core.di

import com.grappim.cashier.core.navigation.FlowRouterImpl
import com.grappim.navigation.router.FlowRouter
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        FeatureNavigationModule::class
    ]
)
interface FeatureNavigationBindsModule {
    @Binds
    fun bindFeatureCiceroneHolder(featureCiceroneHolderImpl: FlowRouterImpl): FlowRouter
}
