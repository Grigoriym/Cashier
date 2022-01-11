package com.grappim.network.di.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grappim.common.di.GsonScope
import com.grappim.network.api.IgnoreFieldsExclusionStrategy
import dagger.Module
import dagger.Provides

@Module
class GsonModule {

    @[GsonScope Provides]
    fun provideGson(
        exclusionStrategy: IgnoreFieldsExclusionStrategy
    ): Gson = GsonBuilder()
        .setLenient()
        .addSerializationExclusionStrategy(exclusionStrategy)
        .addDeserializationExclusionStrategy(exclusionStrategy)
        .create()

}