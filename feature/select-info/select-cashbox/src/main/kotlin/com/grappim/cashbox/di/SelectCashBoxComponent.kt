package com.grappim.cashbox.di

import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FragmentScope
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.Component

@[
FragmentScope Component(
    modules = [
        SelectCashBoxBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SelectCashBoxDeps::class
    ]
)
]
interface SelectCashBoxComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}
