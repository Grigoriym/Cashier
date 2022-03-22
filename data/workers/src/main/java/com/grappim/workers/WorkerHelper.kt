package com.grappim.workers

import android.content.Context
import androidx.work.*
import com.grappim.common.di.AppScope
import com.grappim.common.di.ApplicationContext
import javax.inject.Inject

@AppScope
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
                    buildWorkRequest<CategoriesWorker>(),
                    buildWorkRequest<FeatureToggleWorker>()
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