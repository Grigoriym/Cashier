package com.grappim.sign_up_presentation.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.FlowRouter
import dagger.Component

@[FeatureScope Component(
    modules = [
        SignUpBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [
        SignUpDeps::class
    ]
)]
internal interface SignUpComponent {

    fun flowRouter(): FlowRouter
    fun multiViewModelFactory(): MultiViewModelFactory

}