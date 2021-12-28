package com.grappim.cashier.core.navigation

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.grappim.di.ActivityScope
import com.grappim.navigation.DeepLinkDestination
import com.grappim.navigation.MainNavGraphDirections
import com.grappim.navigation.deepLinkNavigateTo
import com.grappim.auth.di.AuthScreenNavigator
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.menu.di.MenuScreenNavigator
import com.grappim.cashbox.di.SelectCashBoxNavigator
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
import com.grappim.stock.di.SelectStockScreenNavigator
import com.grappim.sign_up.di.SignUpScreenNavigator
import com.grappim.sales.di.SalesScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@ActivityScope
class NavigationManager @Inject constructor(
    private val fragmentManager: FragmentManager,
    private val navController: Lazy<NavController>
) : AuthScreenNavigator, SignUpScreenNavigator,
    SelectStockScreenNavigator,
    SelectCashBoxNavigator,
    MenuScreenNavigator,
    SalesScreenNavigator,
    BagScreenNavigator,
    PaymentMethodScreenNavigator {

    override fun goToSignUp() {
        navController.get().navigate(MainNavGraphDirections.actionAuthFlowToRegisterFlow())
    }

    override fun goToSelectStock() {
        navController.get().navigate(MainNavGraphDirections.actionAuthFlowToStockFlow())
    }

    override fun goToAuth() {
        navController.get().deepLinkNavigateTo(
            DeepLinkDestination.RegisterToAuthFlow
        )
    }

    override fun goToSelectCashier() {
        navController
            .get()
            .navigate(
                MainNavGraphDirections.actionSelectStockFlowToSelectCashboxFlow()
            )
    }

    override fun goToMenu() {
        navController.get().navigate(MainNavGraphDirections.actionSelectCashboxFlowToMenuFlow())
    }

    override fun goToWaybill() {
        navController.get().navigate(MainNavGraphDirections.actionMainFlowToWaybillFlow())
    }

    override fun goToProducts() {
        navController.get().navigate(MainNavGraphDirections.actionMenuFlowToProductsFlow())
    }

    override fun goToSales() {
        navController.get().navigate(MainNavGraphDirections.actionMenuFlowToSalesFlow())
    }

    override fun goToBag() {
        navController.get().navigate(MainNavGraphDirections.actionSalesFlowToBagFlow())
    }

    override fun goToScannerFromSales() {
    }

    override fun goToPaymentMethod() {
        navController.get().navigate(MainNavGraphDirections.actionBagFlowToPaymentMethodFlow())
    }

    override fun goToScannerFromBag() {

    }

    override fun fromPaymentMethodToSales() {
        navController.get().deepLinkNavigateTo(
            DeepLinkDestination.PaymentMethodFlowToSalesFlow
        )
    }

    override fun goBack() {
        navController.get().popBackStack()
    }
}