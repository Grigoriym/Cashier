package com.grappim.cashier.di.root_activity

import com.grappim.auth.di.AuthDeps
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.di.deps.ComponentDeps
import com.grappim.di.deps.ComponentDepsKey
import com.grappim.menu.di.MenuDeps
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityDepsModule {

    @[Binds IntoMap ComponentDepsKey(AuthDeps::class)]
    fun bindAuthDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SignUpDeps::class)]
    fun bindSignUpDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectStockDeps::class)]
    fun bindSelectStockDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectCashBoxDeps::class)]
    fun bindSelectCashierDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(MenuDeps::class)]
    fun bindMenuDeps(
        component: RootActivityComponent
    ): ComponentDeps
}