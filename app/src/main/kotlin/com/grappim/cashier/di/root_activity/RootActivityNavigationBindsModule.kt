package com.grappim.cashier.di.root_activity

import com.grappim.cashier.core.navigation.NavigationManager
import com.grappim.auth.di.AuthScreenNavigator
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.menu.di.MenuScreenNavigator
import com.grappim.sales.di.SalesScreenNavigator
import com.grappim.cashbox.di.SelectCashBoxNavigator
import com.grappim.cashier.di.splash.SplashScreenNavigator
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator
import com.grappim.products.root.di.ProductsScreenNavigator
import com.grappim.stock.di.SelectStockScreenNavigator
import com.grappim.sign_up.di.SignUpScreenNavigator
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator
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
    fun bindSelectStockScreenNavigator(
        navigationManager: NavigationManager
    ): SelectStockScreenNavigator

    @Binds
    fun bindSelectCashierNavigator(
        navigationManager: NavigationManager
    ): SelectCashBoxNavigator

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
    fun bindWaybillScreenNavigator(
        navigationManager: NavigationManager
    ): WaybillScreenNavigator

    @Binds
    fun bindProductsScreenNavigator(
        navigationManager: NavigationManager
    ): ProductsScreenNavigator

    @Binds
    fun bindProductCategoryScreenNavigator(
        navigationManager: NavigationManager
    ): ProductCategoryScreenNavigator
}