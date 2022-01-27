package com.grappim.waybill.ui.root.di

import android.os.Bundle
import com.grappim.navigation.directions.common.CommonScreenNavigator

interface WaybillScreenNavigator : CommonScreenNavigator {

    fun goToWaybillDetails(args: Bundle)
    fun goToWaybillScanner()
    fun goToProductSearch()
    fun goToWaybillProduct()

    fun goFromDetailsToList()
    fun goFromDetailsToScanner()

}