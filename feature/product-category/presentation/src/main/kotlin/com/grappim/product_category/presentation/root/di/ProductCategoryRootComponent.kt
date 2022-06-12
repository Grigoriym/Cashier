package com.grappim.product_category.presentation.root.di

import androidx.fragment.app.FragmentManager
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationBindsModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.product_category.domain.di.ProductCategoryInteractorModule
import com.grappim.product_category.presentation.create_edit.di.CreateEditProductCategoryDeps
import com.grappim.product_category.presentation.list.di.ProductCategoryListDeps
import dagger.BindsInstance
import dagger.Component

@[FeatureScope Component(
    modules = [
        ProductCategoryRootBindsModule::class,
        ProductCategoryRootDepsModule::class,
        FeatureNavigationBindsModule::class,
        ProductCategoryInteractorModule::class
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

}