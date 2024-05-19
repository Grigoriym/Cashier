package com.grappim.cashier.feature.paymentmethod.presentation.di

import androidx.fragment.app.FragmentManager
import com.grappim.calculations.DecimalFormatModule
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationBindsModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.BindsInstance
import dagger.Component

@[FeatureScope Component(
    modules = [
        PaymentMethodBindsModule::class,
        CoroutinesModule::class,
        DecimalFormatModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        PaymentMethodDeps::class
    ]
)]
interface PaymentMethodComponent {

    @Component.Factory
    interface Factory {
        fun create(
            paymentMethodDeps: PaymentMethodDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): PaymentMethodComponent
    }

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory

}
