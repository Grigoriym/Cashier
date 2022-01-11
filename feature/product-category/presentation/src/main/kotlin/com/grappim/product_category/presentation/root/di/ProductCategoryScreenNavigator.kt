package com.grappim.product_category.presentation.root.di

import com.grappim.navigation.directions.common.CommonScreenNavigator

interface ProductCategoryScreenNavigator : CommonScreenNavigator {

    fun goToProductCategoryDetails()
    fun goToCreateProductCategory()

}