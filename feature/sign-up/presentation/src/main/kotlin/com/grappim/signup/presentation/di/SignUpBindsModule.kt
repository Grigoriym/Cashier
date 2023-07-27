package com.grappim.signup.presentation.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.signup.domain.repository.SignUpRepository
import com.grappim.signup.presentation.ui.viewmodel.SignUpViewModel
import com.grappim.signup.presentation.ui.viewmodel.SignUpViewModelImpl
import com.grappim.signup.repository.SignUpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignUpBindsModule {

    @[Binds IntoMap ViewModelKey(SignUpViewModel::class)]
    fun provideSignUpViewModel(
        signUpViewModel: SignUpViewModelImpl
    ): ViewModel

    @Binds
    fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository
}
