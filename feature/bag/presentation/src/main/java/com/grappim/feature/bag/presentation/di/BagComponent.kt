package com.grappim.feature.bag.presentation.di

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
        BagBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        BagDeps::class
    ]
)
]
interface BagComponent {

    @Component.Factory
    interface Factory {
        fun create(
            bagDeps: BagDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): BagComponent
    }

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}
