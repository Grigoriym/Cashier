package com.grappim.cashier.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.db.dao.CategoryDao
import com.grappim.cashier.data.remote.model.category.CategoryMapper.toDomain
import com.grappim.cashier.data.remote.model.category.FilterCategoriesRequestDTO
import com.grappim.cashier.data.remote.model.product.GetProductsRequestDTO
import com.grappim.cashier.data.remote.model.product.ProductsMapper.toDomain
import com.grappim.cashier.di.modules.QualifierCashierApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class CategoriesWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
    private val categoryDao: CategoryDao,
    private val coroutineContextProvider: CoroutineContextProvider
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val CATEGORIES_LIMIT = 20L
    }

    override suspend fun doWork(): Result = withContext(coroutineContextProvider.io) {
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
                if (response.categories?.isNotEmpty() == true) {
                    categoryDao.insert(response.categories.toDomain())
                    newOffset += CATEGORIES_LIMIT
                } else {
                    productsLoaded = true
                }
            }

            Timber.d("worker CategoriesWorker success")
            Result.success()
        } catch (e: Throwable) {
            Timber.e(e)
            Result.failure()
        }
    }
}