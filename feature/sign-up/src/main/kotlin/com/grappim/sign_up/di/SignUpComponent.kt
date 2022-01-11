package com.grappim.sign_up.di

import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
import com.grappim.sign_up.ui.SignUpFragment
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

    fun inject(signUpFragment: SignUpFragment)

}