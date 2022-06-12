package com.grappim.sign_up.domain.di

import com.grappim.sign_up.domain.interactor.SignUpUseCase
import com.grappim.sign_up.domain.interactor.SignUpUseCaseImpl
import com.grappim.sign_up.domain.interactor.ValidateSignUpFieldsUseCase
import com.grappim.sign_up.domain.interactor.ValidateSignUpFieldsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface SignUpInteractorModule {

    @Binds
    fun bindSignUpUseCase(
        signUpUseCaseImpl: SignUpUseCaseImpl
    ): SignUpUseCase

    @Binds
    fun bindValidateSignUpFieldsUseCase(
        validateSignUpFieldsUseCaseImpl: ValidateSignUpFieldsUseCaseImpl
    ): ValidateSignUpFieldsUseCase
}