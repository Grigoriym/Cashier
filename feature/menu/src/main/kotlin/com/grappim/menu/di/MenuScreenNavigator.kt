package com.grappim.menu.di

import com.grappim.navigation.CommonScreenNavigator

interface MenuScreenNavigator : CommonScreenNavigator {

    fun goToWaybill()
    fun goToProducts()
    fun goToSales()
    fun goToProductCategories()

}