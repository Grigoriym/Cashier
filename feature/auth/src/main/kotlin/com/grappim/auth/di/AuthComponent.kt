package com.grappim.auth.di

import com.grappim.auth.ui.view.AuthFragment
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureScope
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