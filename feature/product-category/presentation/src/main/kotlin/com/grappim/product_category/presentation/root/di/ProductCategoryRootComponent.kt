package com.grappim.product_category.presentation.root.di

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureNavController
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.product_category.presentation.create_edit.di.CreateEditProductCategoryDeps
import com.grappim.product_category.presentation.list.di.ProductCategoryListDeps
import dagger.BindsInstance
import dagger.Component

@[FeatureScope Component(
    modules = [
        ProductCategoryRootBindsModule::class,
        ProductCategoryRootDepsModule::class,
        ProductCategoryModule::class
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
            productCategoryRootDeps: ProductCategoryRootDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): ProductCategoryRootComponent
    }

    fun deps(): ComponentDependenciesProvider
    fun multiViewModelFactory(): MultiViewModelFactory

    @FeatureNavController
    fun navController(): NavController

}