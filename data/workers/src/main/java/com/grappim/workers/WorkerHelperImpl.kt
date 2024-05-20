package com.grappim.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.grappim.cashier.common.di.AppScope
import com.grappim.cashier.common.di.ApplicationContext
import com.grappim.cashier.data.workersapi.WorkerHelper
import javax.inject.Inject

@AppScope
class WorkerHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WorkerHelper {

    companion object {
        private const val UNIQUE_WORK_MAIN = "unique_work_main"
    }

    @Suppress("UnusedPrivateMember")
    private fun getMainWorker() = WorkManager.getInstance(context)
        .getWorkInfosForUniqueWorkLiveData(UNIQUE_WORK_MAIN)

    override fun startMainWorkers() {
        WorkManager.getInstance(context)
            .beginUniqueWork(
                UNIQUE_WORK_MAIN,
                ExistingWorkPolicy.KEEP,
                listOf(
                    buildWorkRequest<ProductsWorker>(),
                    buildWorkRequest<CategoriesWorker>(),
                    buildWorkRequest<FeatureToggleWorker>()
                )
            ).enqueue()
    }

    override fun stopMainWorkers() {
        WorkManager.getInstance(context)
            .cancelUniqueWork(UNIQUE_WORK_MAIN)
    }

    override fun startTokenRefresherWorker() {
        startTokenRefresher(context)
    }

    private inline fun <reified T : ListenableWorker> buildWorkRequest(): OneTimeWorkRequest =
        OneTimeWorkRequestBuilder<T>()
            .setConstraints(
                Constraints
                    .Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            ).build()
}
