package com.grappim.workers

import android.content.Context
import androidx.work.*
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logD
import com.grappim.logger.logE
import com.grappim.network.api.AuthApi
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierAuthApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.model.login.SendTokenToRefreshRequestDTO
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

private const val UNIQUE_SEND_REFRESH_TOKEN_WORKER = "unique_send_refresh_token_worker"

class SendTokenRefreshWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierCashierApi private val cashierApi: CashierApi,
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
        .setInitialDelay(10, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            UNIQUE_SEND_REFRESH_TOKEN_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
}