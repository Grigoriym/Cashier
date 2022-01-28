package com.grappim.product_category.presentation.root.di

import android.os.Bundle
import com.grappim.navigation.CommonScreenNavigator

interface ProductCategoryScreenNavigator : CommonScreenNavigator {

    fun goToEditProductCategory(args: Bundle)
    fun goToCreateProductCategory()

}