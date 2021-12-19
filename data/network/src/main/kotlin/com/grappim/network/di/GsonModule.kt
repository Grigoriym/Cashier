package com.grappim.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grappim.network.api.IgnoreFieldsExclusionStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @[Singleton Provides]
    fun provideGson(
        ignoreFieldsStrategy: IgnoreFieldsExclusionStrategy
    ): Gson = GsonBuilder()
        .setLenient()
        .addSerializationExclusionStrategy(ignoreFieldsStrategy)
        .addDeserializationExclusionStrategy(ignoreFieldsStrategy)
        .create()

    @[Singleton Provides]
    fun provideExclusionStrategy(): IgnoreFieldsExclusionStrategy =
        IgnoreFieldsExclusionStrategy()

}