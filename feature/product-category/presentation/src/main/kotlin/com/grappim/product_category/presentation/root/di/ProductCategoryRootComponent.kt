package com.grappim.product_category.presentation.root.di

import com.grappim.common.di.FeatureScope
import com.grappim.product_category.presentation.create_edit.di.CreateEditProductCategoryDeps
import com.grappim.product_category.presentation.list.di.ProductCategoryListDeps
import com.grappim.product_category.presentation.root.ui.ProductCategoryRootFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        ProductCategoryRootBindsModule::class,
        ProductCategoryRootDepsModule::class
    ],
    dependencies = [
        ProductCategoryRootDeps::class
    ]
)]
interface ProductCategoryRootComponent :
    ProductCategoryListDeps,
    CreateEditProductCategoryDeps {

    @Component.Factory
    interface Factory {
        fun create(
            productCategoryRootDeps: ProductCategoryRootDeps
        ): ProductCategoryRootComponent
    }

    fun inject(productCategoryRootFragment: ProductCategoryRootFragment)

}