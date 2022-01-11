package com.grappim.workers.di

import android.util.Log
import androidx.work.Configuration
import com.grappim.common.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class WorkersModule {

    @[AppScope Provides]
    fun provideWorkManagerConfiguration(
        workersFactory: WorkersFactory
    ): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workersFactory)
            .build()
}