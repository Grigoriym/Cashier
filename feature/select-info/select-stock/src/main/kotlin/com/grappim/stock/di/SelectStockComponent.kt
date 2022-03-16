package com.grappim.stock.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FragmentScope
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FragmentScope Component(
    modules = [
        CoroutinesModule::class,
        SelectStockBindsModule::class
    ],
    dependencies = [
        SelectStockDeps::class
    ]
)]
interface SelectStockComponent {

    fun multiViewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}