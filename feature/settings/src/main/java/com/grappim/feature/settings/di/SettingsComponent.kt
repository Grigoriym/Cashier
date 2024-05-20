package com.grappim.feature.settings.di

import androidx.fragment.app.FragmentManager
import com.grappim.cashier.common.async.di.CoroutinesModule
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureScope
import com.grappim.cashier.core.di.FeatureNavigationBindsModule
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import dagger.BindsInstance
import dagger.Component

@[
FeatureScope Component(
    modules = [
        SettingsBindsModule::class,
        CoroutinesModule::class,
        FeatureNavigationBindsModule::class
    ],
    dependencies = [
        SettingsDeps::class
    ]
)
]
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
