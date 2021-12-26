package com.grappim.auth.di

import com.grappim.auth.ui.AuthFragment
import com.grappim.core.di.components_deps.navigation.AuthNavigationDeps
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import dagger.Component

@[FeatureScope Component(
    modules = [
        AuthBindsModule::class,
        CoroutinesModule::class
    ],
    dependencies = [
        AuthDeps::class,
        AuthNavigationDeps::class
    ]
)]
internal interface AuthComponent {

    @Component.Builder
    interface Builder {
        fun deps(authDeps: AuthDeps): Builder
        fun navDeps(authNavigationDeps: AuthNavigationDeps): Builder

        fun build(): AuthComponent
    }

    fun inject(authFragment: AuthFragment)

}