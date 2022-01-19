package com.grappim.sign_up_presentation.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.vm.MultiViewModelFactory
import dagger.Component

@[FeatureScope Component(
    modules = [
        SignUpBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        SignUpDeps::class
    ]
)]
internal interface SignUpComponent {

    fun multiViewModelFactory(): MultiViewModelFactory

}