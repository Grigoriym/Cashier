package com.grappim.signup.presentation.di

import androidx.fragment.app.FragmentManager
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
        SignUpBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        SignUpDeps::class
    ]
)
]
internal interface SignUpComponent {

    @Component.Factory
    interface Factory {
        fun create(
            signUpDeps: SignUpDeps,
            @[BindsInstance FeatureFragmentManager] fragmentManager: FragmentManager
        ): SignUpComponent
    }

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory
}
