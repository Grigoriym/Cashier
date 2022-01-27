package com.grappim.waybill.ui.product.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FragmentScope Component(
    modules = [
        WaybillProductBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        WaybillProductDeps::class
    ]
)]
interface WaybillProductComponent {

    fun multiViewModelFactory(): MultiViewModelFactory

}