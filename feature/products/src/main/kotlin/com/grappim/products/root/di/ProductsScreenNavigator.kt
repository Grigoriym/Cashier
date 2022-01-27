package com.grappim.products.root.di

import android.os.Bundle
import com.grappim.navigation.directions.common.CommonScreenNavigator

interface ProductsScreenNavigator : CommonScreenNavigator {

    fun goToCreateProduct()
    fun goToEditProduct(args: Bundle)

}