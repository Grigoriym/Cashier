package com.grappim.product_category.presentation.list.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.product_category.presentation.list.ui.ProductCategoryListFragment
import dagger.Component

@[FragmentScope Component(
    modules = [
        ProductCategoryListBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        ProductCategoryListDeps::class
    ]
)]
interface ProductCategoryListComponent {

    fun inject(productCategoryListFragment: ProductCategoryListFragment)

}