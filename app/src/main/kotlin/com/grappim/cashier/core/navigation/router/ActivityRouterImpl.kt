package com.grappim.cashier.core.navigation.router

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.grappim.cashier.R
import com.grappim.common.di.ActivityFragmentManager
import com.grappim.common.di.ActivityNavigatorHolder
import com.grappim.common.di.ActivityRouterQualifier
import com.grappim.common.di.ActivityScope
import com.grappim.core.base.BaseFragment
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import javax.inject.Inject

@ActivityScope
class ActivityRouterImpl @Inject constructor(
    private val cashierScreens: CashierScreens,
    @ActivityRouterQualifier override val router: Router,
    @ActivityNavigatorHolder private val navigatorHolder: NavigatorHolder,
    @ActivityFragmentManager private val fragmentManager: FragmentManager
) : ActivityRouter {

    private val currentFragment: BaseFragment<*>?
        get() = fragmentManager.findFragmentById(R.id.nav_host_fragment)
                as? BaseFragment<*>

    override fun setNavigator(navigator: Navigator) {
        navigatorHolder.setNavigator(navigator)
    }

    override fun removeNavigator() {
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: router.exit()
    }

    override fun returnToInitialScreenOnAuthError() {
        router.newRootScreen(cashierScreens.AuthScreen())
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