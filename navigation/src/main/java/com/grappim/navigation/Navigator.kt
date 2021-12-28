package com.grappim.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.grappim.di.ActivityScope
import dagger.Lazy
import javax.inject.Inject

@ActivityScope
@Deprecated(
    message = "use new NavigationManager"
)
class Navigator @Inject constructor(
    private val fragmentManager: FragmentManager,
    private val navController: Lazy<NavController>
) {

    fun popBackStack() {
        navController.get().popBackStack()
    }

    fun navigate(@IdRes resId: Int, args: Bundle? = null) {
        navController.get().navigate(resId, args)
    }

    fun navigate(directions: NavDirections) {
        navController.get().navigate(directions)
    }

    @MainThread
    fun navigateToFlow(
        navigationFlow: NavigationFlow
    ) = when (navigationFlow) {
        NavigationFlow.SelectInfoStockFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionAuthFlowToStockFlow())
        }
        NavigationFlow.RegisterFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionAuthFlowToRegisterFlow())
        }
        NavigationFlow.RegisterToAuthFlow -> {
            navController.get().deepLinkNavigateTo(
                DeepLinkDestination.RegisterToAuthFlow
            )
        }
        NavigationFlow.SelectInfoCashierFlow -> {
            navController.get()
                .navigate(MainNavGraphDirections.actionSelectStockFlowToSelectCashboxFlow())
        }
        NavigationFlow.MenuFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionSelectCashboxFlowToMenuFlow())
        }
        NavigationFlow.WaybillFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionMainFlowToWaybillFlow())
        }
        NavigationFlow.BagFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionSalesFlowToBagFlow())
        }
        NavigationFlow.PaymentMethod -> {
            navController.get().navigate(MainNavGraphDirections.actionBagFlowToPaymentMethodFlow())
        }
        NavigationFlow.SalesFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionMenuFlowToSalesFlow())
        }
        NavigationFlow.PaymentMethodToSales -> {
            navController.get().deepLinkNavigateTo(
                DeepLinkDestination.PaymentMethodFlowToSalesFlow
            )
        }
        NavigationFlow.ProductsFlow -> {
            navController.get().navigate(MainNavGraphDirections.actionMenuFlowToProductsFlow())
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