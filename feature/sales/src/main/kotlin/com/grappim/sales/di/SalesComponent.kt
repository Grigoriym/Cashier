package com.grappim.sales.di

import androidx.fragment.app.FragmentManager
import com.grappim.calculations.DecimalFormatModule
import com.grappim.cashier.common.async.di.CoroutinesModule
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
        CoroutinesModule::class,
        SalesBindsModule::class,
        DecimalFormatModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        SalesDeps::class
    ]
)
]
internal interface SalesComponent {

    @Component.Factory
    interface Factory {
        fun create(
            salesDeps: SalesDeps,
            @[BindsInstance FeatureFragmentManager] fragmentManager: FragmentManager
        ): SalesComponent
    }

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}
