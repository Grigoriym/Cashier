package com.grappim.cashier.di.modules

import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.cashier.di.component.ApplicationComponent
import com.grappim.core.di.RootActivityDeps
import com.grappim.core.di.components_deps.ComponentDeps
import com.grappim.core.di.components_deps.ComponentDepsKey
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import di.AuthDeps

@Module
internal interface ComponentDepsModule {

    @[Binds IntoMap ComponentDepsKey(RootActivityDeps::class)]
    fun provideRootActivityDeps(
        component: ApplicationComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(AuthDeps::class)]
    fun provideAuthDeps(
        applicationComponent: ApplicationComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectStockDeps::class)]
    fun provideSelectStockDeps(
        applicationComponent: ApplicationComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectCashBoxDeps::class)]
    fun provideSelectCashBoxDeps(
        applicationComponent: ApplicationComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SignUpDeps::class)]
    fun provideSignUpDeps(
        applicationComponent: ApplicationComponent
    ): ComponentDeps
}