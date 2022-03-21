package com.grappim.auth.di

import androidx.fragment.app.FragmentManager
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
        AuthBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        AuthDeps::class
    ]
)]
internal interface AuthComponent {

    @Component.Factory
    interface Factory {
        fun create(
            authDeps: AuthDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): AuthComponent
    }

    fun viewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter

}