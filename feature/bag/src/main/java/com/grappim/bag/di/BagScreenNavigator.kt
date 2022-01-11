package com.grappim.bag.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface BagScreenNavigator : CommonScreenNavigator {

    fun goToPaymentMethod()

    fun goToScannerFromBag()

}