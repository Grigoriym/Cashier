package com.grappim.cashier.di.app

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.cashier.common.di.deps.ComponentDepsKey
import com.grappim.cashier.di.rootactivity.RootActivityDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AppComponentDepsModule {

    @[Binds IntoMap ComponentDepsKey(RootActivityDeps::class)]
    fun provideRootActivityDeps(component: ApplicationComponent): ComponentDeps
}
