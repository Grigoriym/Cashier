package com.grappim.menu.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FeatureScope Component(
    modules = [
        MenuBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [
        MenuDeps::class
    ]
)]
internal interface MenuComponent {

    fun viewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter

}