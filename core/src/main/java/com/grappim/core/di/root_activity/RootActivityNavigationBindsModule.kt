package com.grappim.core.di.root_activity

import com.grappim.core.navigation.NavigationManager
import com.grappim.navigation.directions.auth.AuthScreenNavigator
import com.grappim.navigation.directions.select_cashier.SelectCashBoxNavigator
import com.grappim.navigation.directions.select_stock.SelectStockScreenNavigator
import com.grappim.navigation.directions.sign_up.SignUpScreenNavigator
import dagger.Binds
import dagger.Module

@Module
interface RootActivityNavigationBindsModule {

    @Binds
    fun provideAuthScreenNavigator(
        navigationManager: NavigationManager
    ): AuthScreenNavigator

    @Binds
    fun provideSignUpScreenNavigator(
        navigationManager: NavigationManager
    ): SignUpScreenNavigator

    @Binds
    fun bindSelectStockScreenNavigator(
        navigationManager: NavigationManager
    ): SelectStockScreenNavigator

    @Binds
    fun bindSelectCashierNavigator(
        navigationManager: NavigationManager
    ): SelectCashBoxNavigator
}