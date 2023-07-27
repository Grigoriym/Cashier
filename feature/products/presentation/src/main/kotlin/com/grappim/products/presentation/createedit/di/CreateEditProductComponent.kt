package com.grappim.products.presentation.createedit.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.products.presentation.createedit.ui.viewmodel.CreateEditProductViewModelImpl
import dagger.Component

@[FragmentScope Component(
    modules = [
        CreateEditProductBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        CreateEditProductDeps::class
    ]
)]
interface CreateEditProductComponent {

    fun provideCreateProductAssistedViewModelFactory(): CreateEditProductViewModelImpl.Factory
    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}
