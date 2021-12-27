package com.grappim.sign_up.di

import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
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