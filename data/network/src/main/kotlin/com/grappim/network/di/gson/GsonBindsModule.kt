package com.grappim.network.di.gson

import com.google.gson.ExclusionStrategy
import com.grappim.network.api.IgnoreFieldsExclusionStrategy
import dagger.Binds
import dagger.Module

@Module
interface GsonBindsModule {
    @Binds
    fun bindExclusionStrategy(ignoreFieldsExclusionStrategy: IgnoreFieldsExclusionStrategy): ExclusionStrategy
}