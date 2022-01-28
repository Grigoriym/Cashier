package com.grappim.sales.di

import com.grappim.navigation.CommonScreenNavigator

interface SalesScreenNavigator : CommonScreenNavigator {

    fun goToBag()

    fun goToScannerFromSales()

}