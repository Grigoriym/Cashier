package com.grappim.navigation

sealed class NavigationFlow {
    object MainFlow : NavigationFlow()
    object SelectInfoStockFlow : NavigationFlow()
    object SelectInfoCashierFlow : NavigationFlow()
    object WaybillFlow : NavigationFlow()
    object BagFlow : NavigationFlow()
    object PaymentMethod : NavigationFlow()
    object SalesFlow : NavigationFlow()
    object PaymentMethodToSales : NavigationFlow()
    object MenuFlow : NavigationFlow()

    object ProductsFlow : NavigationFlow()
    object CreateEditProductFlow : NavigationFlow()

    object ScannerFlow : NavigationFlow()
    object RegisterFlow : NavigationFlow()
    object RegisterToAuthFlow : NavigationFlow()
}