package com.grappim.cashier.core.navigation.router

import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.grappim.cashier.R
import com.grappim.cashier.common.di.ActivityFragmentManager
import com.grappim.cashier.common.di.ActivityNavigatorHolder
import com.grappim.cashier.common.di.ActivityRouterQualifier
import com.grappim.cashier.common.di.ActivityScope
import com.grappim.cashier.core.base.BaseFragment
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
        router.newRootScreen(cashierScreens.authScreen())
    }

    override fun goToAuth() {
        router.newRootScreen(cashierScreens.authScreen())
    }

    override fun goToSignUpFromSignIn() {
        router.navigateTo(cashierScreens.signUpScreen())
    }

    override fun goToSelectInfo() {
        router.navigateTo(cashierScreens.selectInfoRoot())
    }

    override fun goToMenu() {
        router.navigateTo(cashierScreens.menuScreen())
    }

    override fun goToWaybill() {
        router.navigateTo(cashierScreens.waybill())
    }

    override fun goToProducts() {
        router.navigateTo(cashierScreens.products())
    }

    override fun goToSales() {
        router.navigateTo(cashierScreens.sales())
    }

    override fun goToProductCategories() {
        router.navigateTo(cashierScreens.productCategories())
    }
}
