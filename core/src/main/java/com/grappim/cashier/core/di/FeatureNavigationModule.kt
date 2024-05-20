package com.grappim.cashier.core.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.grappim.cashier.common.di.FeatureCicerone
import com.grappim.cashier.common.di.FeatureNavigatorHolder
import com.grappim.cashier.common.di.FeatureRouterQualifier
import com.grappim.cashier.common.di.FeatureScope
import dagger.Module
import dagger.Provides

@Module
object FeatureNavigationModule {

    @[FeatureScope FeatureCicerone Provides]
    fun provideFlowCicerone(): Cicerone<Router> = Cicerone.create()

    @[FeatureScope FeatureRouterQualifier Provides]
    fun provideFeatureRouter(@FeatureCicerone cicerone: Cicerone<Router>) = cicerone.router

    @[FeatureScope FeatureNavigatorHolder Provides]
    fun provideFeatureNavigatorHolder(@FeatureCicerone cicerone: Cicerone<Router>) =
        cicerone.getNavigatorHolder()
}
