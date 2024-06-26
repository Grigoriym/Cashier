package com.grappim.feature.auth.presentation.di

import androidx.lifecycle.ViewModel
import com.grappim.cashier.core.di.vm.ViewModelKey
import com.grappim.feature.auth.presentation.ui.viewmodel.AuthViewModel
import com.grappim.feature.auth.presentation.ui.viewmodel.AuthViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AuthBindsModule {

    @[Binds IntoMap ViewModelKey(AuthViewModel::class)]
    fun provideAuthViewModel(authViewModelImpl: AuthViewModelImpl): ViewModel
}
