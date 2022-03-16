package com.grappim.scanner.di

import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FeatureScope Component(
    modules = [
        ScannerBindsModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [
        ScannerDeps::class
    ]
)]
internal interface ScannerComponent {

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}