package com.grappim.menu.di

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
        MenuBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        MenuDeps::class
    ]
)]
internal interface MenuComponent {

    @Component.Factory
    interface Factory {
        fun create(
            menuDeps: MenuDeps,
            @BindsInstance @FeatureFragmentManager fragmentManager: FragmentManager
        ): MenuComponent
    }

    fun viewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter

}