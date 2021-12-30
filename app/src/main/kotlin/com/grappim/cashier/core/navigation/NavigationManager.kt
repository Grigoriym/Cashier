package com.grappim.cashier.core.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.grappim.auth.di.AuthScreenNavigator
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.cashbox.di.SelectCashBoxNavigator
import com.grappim.cashier.R
import com.grappim.cashier.di.splash.SplashScreenNavigator
import com.grappim.di.ActivityScope
import com.grappim.menu.di.MenuScreenNavigator
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
import com.grappim.sales.di.SalesScreenNavigator
import com.grappim.sign_up.di.SignUpScreenNavigator
import com.grappim.stock.di.SelectStockScreenNavigator
import com.grappim.waybill.di.WaybillScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@ActivityScope
class NavigationManager @Inject constructor(
    private val supportFragmentManager: FragmentManager,
    private val navController: Lazy<NavController>
) : AuthScreenNavigator, SignUpScreenNavigator,
    SelectStockScreenNavigator,
    SelectCashBoxNavigator,
    MenuScreenNavigator,
    SalesScreenNavigator,
    BagScreenNavigator,
    PaymentMethodScreenNavigator,
    WaybillScreenNavigator,
    SplashScreenNavigator {

    private fun navigateTo(directions: NavDirections) {
        navController
            .get()
            .navigate(directions)
    }

    private fun navigateTo(@IdRes resId: Int) {
        navController
            .get()
            .navigate(resId)
    }

    private fun navigateBack() {
        navController.get().popBackStack()
    }

    override fun goToAuthFromSplash() {
        navigateTo(R.id.action_splash_to_auth)
    }

    override fun goToSignUp() {
        navigateTo(R.id.action_authFlow_to_signUpFlow)
    }

    override fun goToSelectStock() {
        navigateTo(R.id.action_authFlow_to_stockFlow)
    }

    override fun returnToAuthFromSignUp() {
        navigateBack()
    }

    override fun goToSelectCashBox() {
        navigateTo(R.id.action_selectStockFlow_to_selectCashboxFlow)
    }

    override fun goToMenu() {
        navigateTo(R.id.action_selectCashboxFlow_to_menuFlow)
    }

    override fun goToWaybill() {
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            replace(
//                R.id.nav_host_fragment,
//                WaybillRootFragment(),
//                WaybillRootFragment::class.java.canonicalName
//            )
//        }
        navigateTo(R.id.action_mainFlow_to_waybillFlow)
    }

    override fun goToProducts() {
        navigateTo(R.id.action_menuFlow_to_productsFlow)
    }

    override fun goToSales() {
        navigateTo(R.id.action_menuFlow_to_salesFlow)
    }

    override fun goToBag() {
        navigateTo(R.id.action_salesFlow_to_bagFlow)
    }

    override fun goToScannerFromSales() {
    }

    override fun goToPaymentMethod() {
        navigateTo(R.id.action_bagFlow_to_paymentMethodFlow)
    }

    override fun goToScannerFromBag() {

    }

    override fun fromPaymentMethodToSales() {
        navigateTo(R.id.action_paymentMethodFlow_to_salesFlow)
    }

    override fun goToList() {
    }

    override fun goBack() {
        navigateBack()
    }
}