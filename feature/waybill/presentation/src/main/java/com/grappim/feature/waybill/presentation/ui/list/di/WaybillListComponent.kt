package com.grappim.feature.waybill.presentation.ui.list.di

import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.cashier.datetime.DateTimeModule
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        WaybillListBindsModule::class,
        CoroutinesModule::class,
        DateTimeModule::class
    ],
    dependencies = [
        WaybillListDeps::class
    ]
)
]
interface WaybillListComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
