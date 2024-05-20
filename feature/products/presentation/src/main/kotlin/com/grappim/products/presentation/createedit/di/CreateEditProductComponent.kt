package com.grappim.products.presentation.createedit.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.products.presentation.createedit.ui.viewmodel.CreateEditProductViewModelImpl
import dagger.Component

@[
FragmentScope Component(
    modules = [
        CreateEditProductBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        CreateEditProductDeps::class
    ]
)
]
interface CreateEditProductComponent {

    fun provideCreateProductAssistedViewModelFactory(): CreateEditProductViewModelImpl.Factory
    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}
