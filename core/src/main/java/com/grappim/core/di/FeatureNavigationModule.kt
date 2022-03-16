package com.grappim.core.di

import com.grappim.navigation.FlowRouter
import com.grappim.core.navigation.FlowRouterImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureNavigationModule {

    @Binds
    fun bindFeatureCiceroneHolder(
        featureCiceroneHolderImpl: FlowRouterImpl
    ): FlowRouter

}