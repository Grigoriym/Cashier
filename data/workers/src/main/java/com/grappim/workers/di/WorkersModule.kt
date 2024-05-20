package com.grappim.workers.di

import android.util.Log
import androidx.work.Configuration
import com.grappim.cashier.common.di.AppScope
import com.grappim.cashier.data.workersapi.WorkerHelper
import com.grappim.workers.WorkerHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
object WorkersModule {

    @[AppScope Provides]
    fun provideWorkManagerConfiguration(workersFactory: WorkersFactory): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workersFactory)
            .build()
}

@Module
interface WorkersBindsModule {
    @Binds
    fun bindWorkerHelper(workerHelperImpl: WorkerHelperImpl): WorkerHelper
}
