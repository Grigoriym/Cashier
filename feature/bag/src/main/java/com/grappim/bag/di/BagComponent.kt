package com.grappim.bag.di

import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FeatureScope Component(
    modules = [
        BagBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class
    ],
    dependencies = [
        BagDeps::class
    ]
)]
interface BagComponent {

    fun multiViewModelFactory(): MultiViewModelFactory

}