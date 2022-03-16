package com.grappim.sales.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FeatureScope Component(
    modules = [
        CoroutinesModule::class,
        SalesBindsModule::class,
        DecimalFormatModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [
        SalesDeps::class
    ]
)]
internal interface SalesComponent {
    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory

}
