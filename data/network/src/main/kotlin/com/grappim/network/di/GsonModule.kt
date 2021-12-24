package com.grappim.network.di

import com.google.gson.ExclusionStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grappim.di.AppScope
import com.grappim.network.api.GsonBindsModule
import com.grappim.network.api.IgnoreFieldsExclusionStrategy
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        GsonBindsModule::class
    ]
)
class GsonModule {

    @[AppScope Provides]
    fun provideGson(
        exclusionStrategy: ExclusionStrategy
    ): Gson = GsonBuilder()
        .setLenient()
        .addSerializationExclusionStrategy(exclusionStrategy)
        .addDeserializationExclusionStrategy(exclusionStrategy)
        .create()

}