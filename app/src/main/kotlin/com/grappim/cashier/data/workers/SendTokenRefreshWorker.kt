package com.grappim.cashier.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.model.login.SendTokenToRefreshRequestDTO
import com.grappim.cashier.di.modules.QualifierCashierApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
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
            Timber.d("worker SendTokenRefreshWorker started")
            val response = cashierApi.refreshToken(
                SendTokenToRefreshRequestDTO(generalStorage.getToken())
            )
            generalStorage.setAuthToken(response.token)
            Timber.d("worker SendTokenRefreshWorker finished")
            Result.success()
        } catch (e: Throwable) {
            Timber.e(e)
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