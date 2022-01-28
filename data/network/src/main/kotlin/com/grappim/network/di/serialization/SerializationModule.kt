package com.grappim.network.di.serialization

import com.grappim.common.di.SerializationScope
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json

@Module
object SerializationModule {

    @[Provides SerializationScope]
    fun provideSerialization(): Json =
        Json {
            isLenient = true
            prettyPrint = false
            ignoreUnknownKeys = true
            explicitNulls = false
        }

}