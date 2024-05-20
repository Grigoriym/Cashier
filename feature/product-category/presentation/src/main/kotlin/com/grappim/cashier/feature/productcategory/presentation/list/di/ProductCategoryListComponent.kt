package com.grappim.cashier.feature.productcategory.presentation.list.di

import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        ProductCategoryListBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        ProductCategoryListDeps::class
    ]
)
]
interface ProductCategoryListComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
