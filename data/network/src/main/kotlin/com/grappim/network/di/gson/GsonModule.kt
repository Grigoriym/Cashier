package com.grappim.network.di.gson

import com.google.gson.ExclusionStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grappim.di.GsonScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        GsonBindsModule::class
    ]
)
class GsonModule {

    @[GsonScope Provides]
    fun provideGson(
        exclusionStrategy: ExclusionStrategy
    ): Gson = GsonBuilder()
        .setLenient()
        .addSerializationExclusionStrategy(exclusionStrategy)
        .addDeserializationExclusionStrategy(exclusionStrategy)
        .create()

}