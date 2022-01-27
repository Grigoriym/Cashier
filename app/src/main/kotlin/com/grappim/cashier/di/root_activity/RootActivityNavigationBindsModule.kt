package com.grappim.cashier.di.root_activity

import com.grappim.auth.di.AuthScreenNavigator
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.cashier.core.navigation.NavigationManager
import com.grappim.cashier.di.splash.SplashScreenNavigator
import com.grappim.menu.di.MenuScreenNavigator
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
import com.grappim.sales.di.SalesScreenNavigator
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator
import com.grappim.sign_up_presentation.di.SignUpScreenNavigator
import dagger.Binds
import dagger.Module

@Module
interface RootActivityNavigationBindsModule {

    @Binds
    fun bindSplashScreenNavigator(
        navigationManager: NavigationManager
    ): SplashScreenNavigator

    @Binds
    fun bindAuthScreenNavigator(
        navigationManager: NavigationManager
    ): AuthScreenNavigator

    @Binds
    fun bindSignUpScreenNavigator(
        navigationManager: NavigationManager
    ): SignUpScreenNavigator

    @Binds
    fun bindMenuScreenNavigator(
        navigationManager: NavigationManager
    ): MenuScreenNavigator

    @Binds
    fun bindSalesScreenNavigator(
        navigationManager: NavigationManager
    ): SalesScreenNavigator

    @Binds
    fun bindBagScreenNavigator(
        navigationManager: NavigationManager
    ): BagScreenNavigator

    @Binds
    fun bindPaymentMethodScreenNavigator(
        navigationManager: NavigationManager
    ): PaymentMethodScreenNavigator

    @Binds
    fun bindSelectInfoFlowScreenNavigator(
        navigationManager: NavigationManager
    ): SelectInfoFlowScreenNavigator
}