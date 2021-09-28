package com.grappim.repository.di

import com.grappim.domain.storage.GeneralStorage
import com.grappim.repository.storage.GeneralStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {

    @Binds
    fun provideGeneralStorage(
        generalStorageImpl: GeneralStorageImpl
    ): GeneralStorage
}