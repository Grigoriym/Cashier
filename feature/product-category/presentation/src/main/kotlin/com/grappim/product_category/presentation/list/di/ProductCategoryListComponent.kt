package com.grappim.product_category.presentation.list.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
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

    fun multiViewModelFactory(): MultiViewModelFactory

}