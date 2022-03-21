package com.grappim.waybill.ui.details.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.date_time.DateTimeModule
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[FragmentScope Component(
    modules = [
        DecimalFormatModule::class,
        CoroutinesModule::class,
        DateTimeModule::class,
        WaybillDetailsBindsModule::class
    ],
    dependencies = [
        WaybillDetailsDeps::class
    ]
)]
interface WaybillDetailsComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter

}