package com.grappim.cashier.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logD
import com.grappim.logger.logE
import com.grappim.network.api.CashierApi
import com.grappim.network.di.QualifierCashierApi
import com.grappim.network.model.login.SendTokenToRefreshRequestDTO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

private const val UNIQUE_SEND_REFRESH_TOKEN_WORKER = "unique_send_refresh_token_worker"

@HiltWorker
class SendTokenRefreshWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            logD("worker SendTokenRefreshWorker started")
            val response = cashierApi.refreshToken(
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
    )
        .setConstraints(constraints)
        .setInitialDelay(10, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            UNIQUE_SEND_REFRESH_TOKEN_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
}