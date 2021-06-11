package com.grappim.cashier.data.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val UNIQUE_WORK_MAIN = "unique_work_main"

        private const val UNIQUE_WORK_PRODUCTS = "unique_work_products"
        private const val UNIQUE_WORK_CATEGORIES = "unique_work_categories"
    }

    fun getMainWorker() =
        WorkManager.getInstance(context)
            .getWorkInfosForUniqueWorkLiveData(UNIQUE_WORK_MAIN)

    fun startMainWorkers() {
        WorkManager.getInstance(context)
            .beginUniqueWork(
                UNIQUE_WORK_MAIN,
                ExistingWorkPolicy.KEEP,
                listOf(
                    buildWorkRequest<ProductsWorker>(),
                    buildWorkRequest<CategoriesWorker>()
                )
            ).enqueue()
    }

    fun stopMainWorkers() {
        WorkManager.getInstance(context)
            .cancelUniqueWork(UNIQUE_WORK_MAIN)
    }

    fun startTokenRefresherWorker() {
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