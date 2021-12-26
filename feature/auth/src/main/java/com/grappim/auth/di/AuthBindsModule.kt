package com.grappim.auth.di

import androidx.lifecycle.ViewModel
import com.grappim.auth.ui.AuthViewModel
import com.grappim.core.di.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AuthBindsModule {

    @[Binds IntoMap ViewModelKey(AuthViewModel::class)]
    fun provideAuthViewModel(authViewModel: AuthViewModel): ViewModel
}