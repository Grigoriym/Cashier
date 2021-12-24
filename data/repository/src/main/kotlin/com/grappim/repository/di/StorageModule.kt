package com.grappim.repository.di

import com.grappim.domain.storage.GeneralStorage
import com.grappim.repository.storage.GeneralStorageImpl
import dagger.Binds
import dagger.Module

@Module
interface StorageModule {

    @Binds
    fun provideGeneralStorage(
        generalStorageImpl: GeneralStorageImpl
    ): GeneralStorage
}