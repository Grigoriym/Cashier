package com.grappim.feature.waybill.presentation.ui.list.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.date_time.DateTimeModule
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[FragmentScope Component(
    modules = [
        WaybillListBindsModule::class,
        CoroutinesModule::class,
        DateTimeModule::class
    ],
    dependencies = [
        WaybillListDeps::class
    ]
)]
interface WaybillListComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter

}