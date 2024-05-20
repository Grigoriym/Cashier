package com.grappim.feature.waybill.presentation.ui.scanner.di

import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        WaybillScannerBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        WaybillScannerDeps::class
    ]
)
]
interface WaybillScannerComponent {
    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
