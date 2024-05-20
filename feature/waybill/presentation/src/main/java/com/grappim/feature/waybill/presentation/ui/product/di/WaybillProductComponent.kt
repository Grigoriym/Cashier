package com.grappim.feature.waybill.presentation.ui.product.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        WaybillProductBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        WaybillProductDeps::class
    ]
)
]
interface WaybillProductComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
