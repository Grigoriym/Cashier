package com.grappim.navigation.router

import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router

interface ActivityRouter {

    fun setNavigator(navigator: Navigator)
    fun removeNavigator()

    val router: Router

    fun onBackPressed()
    fun returnToInitialScreenOnAuthError()

    fun goToAuth()
    fun goToSignUpFromSignIn()
    fun goToSelectInfo()
    fun goToMenu()

    fun goToWaybill()
    fun goToProducts()
    fun goToSales()
    fun goToProductCategories()
}