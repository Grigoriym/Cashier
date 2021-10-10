package com.grappim.navigation

sealed class NavigationFlow {
    object MainFlow : NavigationFlow()
    object WaybillFlow : NavigationFlow()
}