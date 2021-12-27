package com.grappim.core.navigation

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.grappim.di.ActivityScope
import com.grappim.navigation.DeepLinkDestination
import com.grappim.navigation.MainNavGraphDirections
import com.grappim.navigation.deepLinkNavigateTo
import com.grappim.navigation.directions.auth.AuthScreenNavigator
import com.grappim.navigation.directions.menu.MenuScreenNavigator
import com.grappim.navigation.directions.select_cashier.SelectCashBoxNavigator
import com.grappim.navigation.directions.select_stock.SelectStockScreenNavigator
import com.grappim.navigation.directions.sign_up.SignUpScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@ActivityScope
class NavigationManager @Inject constructor(
    private val fragmentManager: FragmentManager,
    private val navController: Lazy<NavController>
) : AuthScreenNavigator, SignUpScreenNavigator,
    SelectStockScreenNavigator,
    SelectCashBoxNavigator,
    MenuScreenNavigator {

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

    override fun goBack() {
        navController.get().popBackStack()
    }
}