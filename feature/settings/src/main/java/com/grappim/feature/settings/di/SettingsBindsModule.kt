package com.grappim.feature.settings.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.feature.settings.ui.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsBindsModule {

    @[Binds IntoMap ViewModelKey(SettingsViewModel::class)]
    fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}