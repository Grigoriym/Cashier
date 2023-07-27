package com.grappim.cashier.di.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.grappim.common.di.ActivityCicerone
import com.grappim.common.di.ActivityNavigatorHolder
import com.grappim.common.di.ActivityRouterQualifier
import com.grappim.common.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object ActivityNavigationModule {

    @[ActivityScope Provides ActivityCicerone]
    fun provideActivityCicerone(): Cicerone<Router> = Cicerone.create()

    @[ActivityScope Provides ActivityRouterQualifier]
    fun provideActivityRouter(
        @ActivityCicerone cicerone: Cicerone<Router>
    ): Router = cicerone.router

    @[ActivityScope Provides ActivityNavigatorHolder]
    fun provideActivityNavigatorHolder(
        @ActivityCicerone cicerone: Cicerone<Router>
    ): NavigatorHolder = cicerone.getNavigatorHolder()
}
