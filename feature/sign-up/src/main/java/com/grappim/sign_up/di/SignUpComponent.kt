package com.grappim.sign_up.di

import com.grappim.core.di.components_deps.navigation.SignUpNavigationDeps
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
        SignUpDeps::class,
        SignUpNavigationDeps::class
    ]
)]
interface SignUpComponent {

    @Component.Builder
    interface Builder {
        fun deps(signUpDeps: SignUpDeps): Builder
        fun navDeps(signUpNavigationDeps: SignUpNavigationDeps): Builder
        fun build(): SignUpComponent
    }

    fun inject(signUpFragment: SignUpFragment)

}