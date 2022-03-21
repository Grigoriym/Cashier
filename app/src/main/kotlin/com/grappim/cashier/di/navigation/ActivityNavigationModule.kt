package com.grappim.cashier.di.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.grappim.common.di.ActivityCicerone
import com.grappim.common.di.ActivityNavigatorHolder
import com.grappim.common.di.ActivityRouterQualifier
import com.grappim.common.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object ActivityNavigationModule {

    @[ActivityScope ActivityCicerone Provides]
    fun provideActivityCicerone(): Cicerone<Router> = Cicerone.create()

    @[ActivityScope ActivityRouterQualifier Provides]
    fun provideActivityRouter(
        @ActivityCicerone cicerone: Cicerone<Router>
    ) = cicerone.router

    @[ActivityScope ActivityNavigatorHolder Provides]
    fun provideActivityNavigatorHolder(
        @ActivityCicerone cicerone: Cicerone<Router>
    ) = cicerone.getNavigatorHolder()
}