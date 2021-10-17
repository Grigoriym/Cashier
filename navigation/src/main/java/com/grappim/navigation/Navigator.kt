package com.grappim.navigation

import androidx.annotation.MainThread
import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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

    @MainThread
    fun navigateToFlow(
        navigationFlow: NavigationFlow
    ) = when (navigationFlow) {
        NavigationFlow.SelectInfoStockFlow -> {
            navController.navigate(MainNavGraphDirections.actionAuthFlowToStockFlow())
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