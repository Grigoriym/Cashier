package com.grappim.sign_up.di

import com.grappim.core.di.components_deps.ComponentDeps
import com.grappim.core.utils.ResourceManager
import com.grappim.di.FeatureScope
import com.grappim.domain.di.CoroutinesModule
import com.grappim.domain.repository.SignUpRepository
import com.grappim.navigation.Navigator
import com.grappim.sign_up.SignUpFragment
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
interface SignUpComponent {

    @Component.Builder
    interface Builder {
        fun deps(signUpDeps: SignUpDeps): Builder
        fun build(): SignUpComponent
    }

    fun inject(signUpFragment: SignUpFragment)

}

interface SignUpDeps : ComponentDeps {
    fun navigator(): Navigator
    fun resourceManager(): ResourceManager
    fun signUpRepository(): SignUpRepository
}