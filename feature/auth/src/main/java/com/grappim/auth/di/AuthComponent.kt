package com.grappim.auth.di

import com.grappim.auth.ui.AuthFragment
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
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

    fun inject(authFragment: AuthFragment)

}