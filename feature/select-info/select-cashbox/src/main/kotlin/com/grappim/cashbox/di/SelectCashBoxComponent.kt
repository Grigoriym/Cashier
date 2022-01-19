package com.grappim.cashbox.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FragmentScope Component(
    modules = [
        SelectCashBoxBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SelectCashBoxDeps::class
    ]
)]
interface SelectCashBoxComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
}