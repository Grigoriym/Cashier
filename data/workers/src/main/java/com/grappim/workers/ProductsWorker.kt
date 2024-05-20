package com.grappim.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.cashier.common.async.di.IoDispatcher
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.logger.logD
import com.grappim.logger.logE
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productsRepository: ProductsRepository
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val PRODUCTS_LIMIT = 20L
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, parameters: WorkerParameters): ProductsWorker
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        return@withContext try {
            var newOffset = 0L
            var productsLoaded = false

            while (!productsLoaded) {
                val products = productsRepository.filterProducts(
                    offset = newOffset,
                    limit = PRODUCTS_LIMIT
                )

                if (products.isNotEmpty()) {
                    productsRepository.insertProducts(products)
                    newOffset += PRODUCTS_LIMIT
                } else {
                    productsLoaded = true
                }
            }
            logD("worker ProductsWorker success")
            Result.success()
        } catch (e: Throwable) {
            logE(e)
            Result.failure()
        }
    }
}
