package com.grappim.core.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.grappim.common.di.FeatureCicerone
import com.grappim.common.di.FeatureNavigatorHolder
import com.grappim.common.di.FeatureRouterQualifier
import com.grappim.common.di.FeatureScope
import dagger.Module
import dagger.Provides

@Module
object FeatureNavigationModule {

    @[FeatureScope FeatureCicerone Provides]
    fun provideFlowCicerone(): Cicerone<Router> = Cicerone.create()

    @[FeatureScope FeatureRouterQualifier Provides]
    fun provideFeatureRouter(
        @FeatureCicerone cicerone: Cicerone<Router>
    ) = cicerone.router

    @[FeatureScope FeatureNavigatorHolder Provides]
    fun provideFeatureNavigatorHolder(
        @FeatureCicerone cicerone: Cicerone<Router>
    ) = cicerone.getNavigatorHolder()
}
