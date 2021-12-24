package com.grappim.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.grappim.di.AppScope
import javax.inject.Inject

@AppScope
class Navigator @Inject constructor(

) {

    private var _navController: NavController? = null
    private val navController: NavController
        get() = requireNotNull(_navController) {
            "navController must not be null"
        }

    fun setNavController(controller: NavController) {
        _navController = controller
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigate(@IdRes resId: Int, args: Bundle? = null) {
        navController.navigate(resId, args)
    }

    fun navigate(directions: NavDirections) {
        navController.navigate(directions)
    }

    @MainThread
    fun navigateToFlow(
        navigationFlow: NavigationFlow
    ) = when (navigationFlow) {
        NavigationFlow.SelectInfoStockFlow -> {
            navController.navigate(MainNavGraphDirections.actionAuthFlowToStockFlow())
        }
        NavigationFlow.RegisterFlow -> {
            navController.navigate(MainNavGraphDirections.actionAuthFlowToRegisterFlow())
        }
        NavigationFlow.RegisterToAuthFlow -> {
            navController.deepLinkNavigateTo(
                DeepLinkDestination.RegisterToAuthFlow
            )
        }
        NavigationFlow.SelectInfoCashierFlow -> {
            navController.navigate(MainNavGraphDirections.actionSelectStockFlowToSelectCashboxFlow())
        }
        NavigationFlow.MenuFlow -> {
            navController.navigate(MainNavGraphDirections.actionSelectCashboxFlowToMenuFlow())
        }
        NavigationFlow.WaybillFlow -> {
            navController.navigate(MainNavGraphDirections.actionMainFlowToWaybillFlow())
        }
        NavigationFlow.BagFlow -> {
            navController.navigate(MainNavGraphDirections.actionSalesFlowToBagFlow())
        }
        NavigationFlow.PaymentMethod -> {
            navController.navigate(MainNavGraphDirections.actionBagFlowToPaymentMethodFlow())
        }
        NavigationFlow.SalesFlow -> {
            navController.navigate(MainNavGraphDirections.actionMenuFlowToSalesFlow())
        }
        NavigationFlow.PaymentMethodToSales -> {
            navController.deepLinkNavigateTo(
                DeepLinkDestination.PaymentMethodFlowToSalesFlow
            )
        }
        NavigationFlow.ProductsFlow -> {
            navController.navigate(MainNavGraphDirections.actionMenuFlowToProductsFlow())
        }
        NavigationFlow.CreateEditProductFlow -> {

        }
        NavigationFlow.ScannerFlow -> {

        }
        NavigationFlow.MainFlow -> {
//            navController.navigate()
        }
    }
}