package com.grappim.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.auth.network.api.AuthApi
import com.grappim.feature.auth.network.di.QualifierAuthApi
import com.grappim.feature.auth.network.models.SendTokenToRefreshRequestDTO
import com.grappim.logger.logD
import com.grappim.logger.logE
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

private const val UNIQUE_SEND_REFRESH_TOKEN_WORKER = "unique_send_refresh_token_worker"
private const val TOKEN_REFRESH_DELAY = 10L

class SendTokenRefreshWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierAuthApi private val authApi: AuthApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val generalStorage: GeneralStorage
) : CoroutineWorker(context, workerParameters) {

    @AssistedFactory
    interface Factory {
        fun create(
            apContext: Context,
            workerParameters: WorkerParameters
        ): SendTokenRefreshWorker
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        try {
            logD("worker SendTokenRefreshWorker started")
            val response = authApi.refreshToken(
                SendTokenToRefreshRequestDTO(generalStorage.getToken())
            )
            generalStorage.setAuthToken(response.token)
            logD("worker SendTokenRefreshWorker finished")
            Result.success()
        } catch (e: Throwable) {
            logE(e)
            Result.failure()
        }
    }
}

fun startTokenRefresher(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val workRequest = PeriodicWorkRequestBuilder<SendTokenRefreshWorker>(
        PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
        TimeUnit.MILLISECONDS
    ).setConstraints(constraints)
        .setInitialDelay(TOKEN_REFRESH_DELAY, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            UNIQUE_SEND_REFRESH_TOKEN_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
}
