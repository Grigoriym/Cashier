package com.grappim.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.logger.logD
import com.grappim.logger.logE
import com.grappim.productcategory.domain.repository.ProductCategoryRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoriesWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productCategoryRepository: ProductCategoryRepository,
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val CATEGORIES_LIMIT = 20L
    }

    @AssistedFactory
    interface Factory {
        fun create(
            appContext: Context,
            workerParameters: WorkerParameters
        ): CategoriesWorker
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        return@withContext try {
            var newOffset = 0L
            var productsLoaded = false

            while (!productsLoaded) {
                val categories = productCategoryRepository.filterCategories(
                    offset = newOffset,
                    limit = CATEGORIES_LIMIT
                )
                if (categories.isNotEmpty()) {
                    productCategoryRepository.insertCategories(categories)
                    newOffset += CATEGORIES_LIMIT
                } else {
                    productsLoaded = true
                }
            }

            logD("worker CategoriesWorker success")
            Result.success()
        } catch (e: Exception) {
            logE(e)
            Result.failure()
        }
    }
}
