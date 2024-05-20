package com.grappim.products.presentation.list.di

import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        ProductListBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        ProductListDeps::class
    ]
)
]
interface ProductsListComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
