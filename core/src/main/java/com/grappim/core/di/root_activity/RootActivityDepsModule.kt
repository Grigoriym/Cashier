package com.grappim.core.di.root_activity

import com.grappim.core.di.components_deps.navigation.*
import com.grappim.di.deps.ComponentDeps
import com.grappim.di.deps.ComponentDepsKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityDepsModule {

    @[Binds IntoMap ComponentDepsKey(NavigationDeps::class)]
    fun bindNavigationDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(AuthNavigationDeps::class)]
    fun bindAuthNavigationDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SignUpNavigationDeps::class)]
    fun bindSignUpNavigationDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectStockNavigationDeps::class)]
    fun bindSelectStockNavigationDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectCashBoxNavigationDeps::class)]
    fun bindSelectCashierNavigationDeps(
        component: RootActivityComponent
    ): ComponentDeps
}