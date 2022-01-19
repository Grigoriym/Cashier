package com.grappim.sign_up_presentation.di

import androidx.lifecycle.ViewModel
import com.grappim.core.di.vm.ViewModelKey
import com.grappim.sign_up.domain.repository.SignUpRepository
import com.grappim.sign_up_presentation.ui.viewmodel.SignUpViewModel
import com.grappim.sign_up_presentation.ui.viewmodel.SignUpViewModelImpl
import com.grappim.sign_up_repository.SignUpRepositoryImpl
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