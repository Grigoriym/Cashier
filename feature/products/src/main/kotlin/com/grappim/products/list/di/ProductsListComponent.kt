package com.grappim.products.list.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FragmentScope Component(
    modules = [
        ProductListBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        ProductListDeps::class
    ]
)]
interface ProductsListComponent {

    fun multiViewModelFactory(): MultiViewModelFactory

}