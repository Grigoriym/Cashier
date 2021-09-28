package com.grappim.cashier.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.db.dao.CategoryDao
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logD
import com.grappim.logger.logE
import com.grappim.network.api.CashierApi
import com.grappim.network.di.QualifierCashierApi
import com.grappim.network.mappers.category.CategoryMapper
import com.grappim.network.model.category.FilterCategoriesRequestDTO
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@HiltWorker
class CategoriesWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
    private val categoryDao: CategoryDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val categoryMapper: CategoryMapper
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val CATEGORIES_LIMIT = 20L
    }

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        return@withContext try {
            var newOffset = 0L
            var productsLoaded = false

            while (!productsLoaded) {
                val request = FilterCategoriesRequestDTO(
                    offset = newOffset,
                    limit = CATEGORIES_LIMIT,
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.getStockId()
                )

                val response = cashierApi.filterCategories(request)
                val categories = response.categories
                if (categories?.isNotEmpty() == true) {
                    val entities = categoryMapper.dtoToEntityList(categories)
                    categoryDao.insert(entities)
                    newOffset += CATEGORIES_LIMIT
                } else {
                    productsLoaded = true
                }
            }

            logD("worker CategoriesWorker success")
            Result.success()
        } catch (e: Throwable) {
            logE(e)
            Result.failure()
        }
    }
}