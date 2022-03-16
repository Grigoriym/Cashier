package com.grappim.bag.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FeatureScope Component(
    modules = [
        BagBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [
        BagDeps::class
    ]
)]
interface BagComponent {

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory

}