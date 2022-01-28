package com.grappim.bag.di

import com.grappim.navigation.CommonScreenNavigator

interface BagScreenNavigator : CommonScreenNavigator {

    fun goToPaymentMethod()

    fun goToScannerFromBag()

}