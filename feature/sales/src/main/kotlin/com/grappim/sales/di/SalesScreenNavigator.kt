package com.grappim.sales.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface SalesScreenNavigator : CommonScreenNavigator {

    fun goToBag()

    fun goToScannerFromSales()

}