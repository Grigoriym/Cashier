package com.grappim.auth.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FeatureScope Component(
    modules = [
        AuthBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        AuthDeps::class
    ]
)]
internal interface AuthComponent {

    fun viewModelFactory(): MultiViewModelFactory

}