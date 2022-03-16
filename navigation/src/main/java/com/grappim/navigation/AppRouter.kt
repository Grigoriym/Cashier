package com.grappim.navigation

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface AppRouter {
    val navigatorHolder: NavigatorHolder
    val router: Router

    fun goBack()

    fun goToAuth()
    fun goToSignUpFromSignIn()
    fun goToSelectInfo()
    fun goToMenu()

    fun goToWaybill()
    fun goToProducts()
    fun goToSales()
    fun goToProductCategories()
}