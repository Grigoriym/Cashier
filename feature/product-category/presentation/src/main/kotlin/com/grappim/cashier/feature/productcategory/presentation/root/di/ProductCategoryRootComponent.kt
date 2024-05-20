package com.grappim.cashier.feature.productcategory.presentation.root.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.core.di.FeatureNavigationBindsModule
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.cashier.feature.productcategory.presentation.createedit.di.CreateEditProductCategoryDeps
import com.grappim.cashier.feature.productcategory.presentation.list.di.ProductCategoryListDeps
import dagger.BindsInstance
import dagger.Component

@[
FeatureScope Component(
    modules = [
        ProductCategoryRootBindsModule::class,
        ProductCategoryRootDepsModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        ProductCategoryRootDeps::class
    ]
)
]
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
