package com.grappim.cashier.di.modules

import com.grappim.db.di.DatabaseModule
import com.grappim.domain.di.DomainModule
import com.grappim.network.di.DataNetworkModule
import com.grappim.repository.di.RepositoryModule
import com.grappim.repository.di.StorageModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DomainModule::class,
        DataNetworkModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        StorageModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule