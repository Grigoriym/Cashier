package com.grappim.navigation.directions.menu

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface MenuScreenNavigator : CommonScreenNavigator {

    fun goToWaybill()
    fun goToProducts()
    fun goToSales()

}