package com.grappim.cashier.di.app

import com.grappim.domain.password.PasswordManager
import com.grappim.repository.password.PasswordManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface PasswordManagerBindModule {

    @Binds
    fun bindPasswordManager(passwordManagerImpl: PasswordManagerImpl): PasswordManager
}
