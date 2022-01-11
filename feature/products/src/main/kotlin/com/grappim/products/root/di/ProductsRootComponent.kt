package com.grappim.products.root.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.products.create_edit.di.CreateEditProductDeps
import com.grappim.products.list.di.ProductListDeps
import com.grappim.products.root.ui.ProductsRootFragment
import dagger.Component

@[FeatureScope Component(
    modules = [
        ProductsRootBindsModule::class,
        ProductsRootDepsModule::class,
        CoroutinesModule::class,
    ],
    dependencies = [
        ProductsRootDeps::class
    ]
)]
interface ProductsRootComponent :
    ProductListDeps,
    CreateEditProductDeps {

    @Component.Factory
    interface Factory {
        fun create(
            productsRootDeps: ProductsRootDeps
        ): ProductsRootComponent
    }

    fun inject(productsRootFragment: ProductsRootFragment)
}