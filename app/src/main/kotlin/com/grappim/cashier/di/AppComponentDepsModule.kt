package com.grappim.cashier.di

import com.grappim.auth.di.AuthDeps
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.cashier.di.ApplicationComponent
import com.grappim.core.di.root_activity.RootActivityDeps
import com.grappim.di.deps.ComponentDeps
import com.grappim.di.deps.ComponentDepsKey
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AppComponentDepsModule {

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