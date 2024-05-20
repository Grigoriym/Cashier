package com.grappim.workers.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.grappim.workers.CategoriesWorker
import com.grappim.workers.FeatureToggleWorker
import com.grappim.workers.ProductsWorker
import com.grappim.workers.SendTokenRefreshWorker
import javax.inject.Inject

class WorkersFactory @Inject constructor(
    private val productsWorkerFactory: ProductsWorker.Factory,
    private val categoriesWorkerFactory: CategoriesWorker.Factory,
    private val sendTokenRefreshWorkerFactory: SendTokenRefreshWorker.Factory,
    private val featureToggleWorkerFactory: FeatureToggleWorker.Factory
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = when (workerClassName) {
        ProductsWorker::class.java.name -> {
            productsWorkerFactory.create(appContext, workerParameters)
        }

        CategoriesWorker::class.java.name -> {
            categoriesWorkerFactory.create(appContext, workerParameters)
        }

        SendTokenRefreshWorker::class.java.name -> {
            sendTokenRefreshWorkerFactory.create(appContext, workerParameters)
        }

        FeatureToggleWorker::class.java.name -> {
            featureToggleWorkerFactory.create(appContext, workerParameters)
        }

        else -> {
            null
        }
    }
}
