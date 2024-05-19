package com.grappim.core.di

import com.grappim.core.navigation.FlowRouterImpl
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
    fun bindFeatureCiceroneHolder(
        featureCiceroneHolderImpl: FlowRouterImpl
    ): FlowRouter
}
