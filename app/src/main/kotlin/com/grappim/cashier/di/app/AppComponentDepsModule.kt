package com.grappim.cashier.di.app

import com.grappim.cashier.di.root_activity.RootActivityDeps
import com.grappim.di.deps.ComponentDeps
import com.grappim.di.deps.ComponentDepsKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AppComponentDepsModule {

    @[Binds IntoMap ComponentDepsKey(RootActivityDeps::class)]
    fun provideRootActivityDeps(
        component: ApplicationComponent
    ): ComponentDeps

}