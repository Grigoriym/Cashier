package com.grappim.feature.auth.repository.di

import com.grappim.feature.auth.repository.remote.AuthRepositoryImpl
import com.grappim.feature.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface AuthRepositoryBindsModule {

    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}