package com.grappim.scanner.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.core.di.FeatureNavigationBindsModule
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.BindsInstance
import dagger.Component

@[
FeatureScope Component(
    modules = [
        ScannerBindsModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        ScannerDeps::class
    ]
)
]
internal interface ScannerComponent {

    @Component.Factory
    interface Factory {
        fun create(
            scannerDeps: ScannerDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): ScannerComponent
    }

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}
