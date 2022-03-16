package com.grappim.waybill.ui.scanner.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FragmentScope Component(
    modules = [
        WaybillScannerBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        WaybillScannerDeps::class
    ]
)]
interface WaybillScannerComponent {
    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}