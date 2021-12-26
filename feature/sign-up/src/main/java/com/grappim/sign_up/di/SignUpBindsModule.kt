package com.grappim.sign_up.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.sign_up.ui.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignUpBindsModule {

    @[Binds IntoMap ViewModelKey(SignUpViewModel::class)]
    fun provideSignUpViewModel(
        signUpViewModel: SignUpViewModel
    ): ViewModel

}