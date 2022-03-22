package com.grappim.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.domain.repository.FeatureToggleRepository
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.logger.logE
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FeatureToggleWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val featureToggleRepository: FeatureToggleRepository,
    private val featureToggleLocalRepository: FeatureToggleLocalRepository
) : CoroutineWorker(context, workerParameters) {

    @AssistedFactory
    interface Factory {
        fun create(
            appContext: Context,
            parameters: WorkerParameters
        ): FeatureToggleWorker
    }

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        try {
            val features = featureToggleRepository.getFeatures()
            featureToggleLocalRepository.updateDataStore(features)
            Result.success()
        } catch (e: Throwable) {
            logE(e)
            Result.failure()
        }
    }

}