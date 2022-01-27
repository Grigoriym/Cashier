package com.grappim.products.root.di

import androidx.fragment.app.FragmentManager
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.products.create_edit.di.CreateEditProductDeps
import com.grappim.products.list.di.ProductListDeps
import dagger.BindsInstance
import dagger.Component

@[FeatureScope Component(
    modules = [
        ProductsRootBindsModule::class,
        ProductsRootDepsModule::class,
        CoroutinesModule::class,
        ProductsModule::class
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
            productsRootDeps: ProductsRootDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): ProductsRootComponent
    }

    fun deps(): ComponentDependenciesProvider
    fun multiViewModelFactory(): MultiViewModelFactory

}