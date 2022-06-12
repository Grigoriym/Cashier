package com.grappim.auth.di

import com.grappim.domain.interactor.login.LoginUseCase
import com.grappim.domain.interactor.login.LoginUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AuthInteractorModule {
    @Binds
    fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase
}