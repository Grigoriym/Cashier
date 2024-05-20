package com.grappim.products.presentation.root.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.core.di.FeatureNavigationBindsModule
import com.grappim.products.presentation.createedit.di.CreateEditProductDeps
import com.grappim.products.presentation.list.di.ProductListDeps
import dagger.BindsInstance
import dagger.Component

@[
FeatureScope Component(
    modules = [
        ProductsRootBindsModule::class,
        ProductsRootDepsModule::class,
        CoroutinesModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        ProductsRootDeps::class
    ]
)
]
interface ProductsRootComponent :
    ProductListDeps,
    CreateEditProductDeps {

    @Component.Factory
    interface Factory {
        fun create(
            productsRootDeps: ProductsRootDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): ProductsRootComponent
    }

    fun deps(): ComponentDependenciesProvider
}
