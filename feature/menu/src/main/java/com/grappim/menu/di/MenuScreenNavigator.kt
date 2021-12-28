package com.grappim.menu.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface MenuScreenNavigator : CommonScreenNavigator {

    fun goToWaybill()
    fun goToProducts()
    fun goToSales()

}