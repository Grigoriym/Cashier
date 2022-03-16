package com.grappim.cashier.core.navigation.router

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.grappim.common.di.ActivityScope
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens
import javax.inject.Inject

@ActivityScope
class AppRouterImpl @Inject constructor(
    private val cashierScreens: CashierScreens
) : AppRouter {

    val cicerone by lazy {
        Cicerone.create()
    }

    override val router: Router by lazy {
        cicerone.router
    }

    override val navigatorHolder by lazy {
        cicerone.getNavigatorHolder()
    }

    override fun goBack() {
        router.exit()
    }

    override fun goToSplash() {
        router.replaceScreen(cashierScreens.SplashScreen())
    }

    override fun goToAuth() {
        router.newRootScreen(cashierScreens.AuthScreen())
    }

    override fun goToSignUpFromSignIn() {
        router.navigateTo(cashierScreens.SignUpScreen())
    }

    override fun goToSelectInfo() {
        router.navigateTo(cashierScreens.SelectInfoRoot())
    }

    override fun goToMenu() {
        router.navigateTo(cashierScreens.MenuScreen())
    }

    override fun goToWaybill() {
        router.navigateTo(cashierScreens.Waybill())
    }

    override fun goToProducts() {
        router.navigateTo(cashierScreens.Products())
    }

    override fun goToSales() {
        router.navigateTo(cashierScreens.Sales())
    }

    override fun goToProductCategories() {
        router.navigateTo(cashierScreens.ProductCategories())
    }

}