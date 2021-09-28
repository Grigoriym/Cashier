package com.grappim.network.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ApiModule::class,
        NetworkModule::class,
        GsonModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object DataNetworkModule