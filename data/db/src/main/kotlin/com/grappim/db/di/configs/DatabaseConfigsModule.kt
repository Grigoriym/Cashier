package com.grappim.db.di.configs

import com.grappim.db.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class DatabaseConfigsModule {

    @Provides
    fun provideDatabaseBuildConfigProvider(): DatabaseBuildConfigProvider =
        DatabaseBuildConfigProvider(
            debug = BuildConfig.DEBUG,
            buildType = BuildConfig.BUILD_TYPE
        )

}