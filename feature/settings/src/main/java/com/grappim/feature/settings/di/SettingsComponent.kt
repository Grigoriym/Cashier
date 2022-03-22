package com.grappim.feature.settings.di

import androidx.fragment.app.FragmentManager
import com.grappim.common.asynchronous.di.CoroutinesModule
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureScope
import com.grappim.core.di.FeatureNavigationBindsModule
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.BindsInstance
import dagger.Component

@[FeatureScope Component(
    modules = [
        SettingsBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        SettingsDeps::class
    ]
)]
interface SettingsComponent {

    @Component.Factory
    interface Factory {
        fun create(
            settingsDeps: SettingsDeps,
            @[BindsInstance FeatureFragmentManager] fragmentManager: FragmentManager
        ): SettingsComponent
    }

    fun viewModelFactory(): MultiViewModelFactory
    fun flowRouter(): FlowRouter
}