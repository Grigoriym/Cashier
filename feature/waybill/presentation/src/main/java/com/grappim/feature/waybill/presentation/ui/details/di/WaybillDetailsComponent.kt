package com.grappim.feature.waybill.presentation.ui.details.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.cashier.datetime.DateTimeModule
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        DecimalFormatModule::class,
        CoroutinesModule::class,
        DateTimeModule::class,
        WaybillDetailsBindsModule::class
    ],
    dependencies = [
        WaybillDetailsDeps::class
    ]
)
]
interface WaybillDetailsComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
