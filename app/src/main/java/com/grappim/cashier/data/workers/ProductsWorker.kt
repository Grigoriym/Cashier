package com.grappim.cashier.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.db.dao.ProductsDao
import com.grappim.cashier.data.remote.model.product.GetProductsRequestDTO
import com.grappim.cashier.data.remote.model.product.ProductsMapper.toDomain
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.di.modules.QualifierCashierApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class ProductsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
    private val productsDao: ProductsDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val PRODUCTS_LIMIT = 20L
    }

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        return@withContext try {
            var newOffset = 0L
            var productsLoaded = false

            while (!productsLoaded) {
                val request = GetProductsRequestDTO(
                    offset = newOffset,
                    limit = PRODUCTS_LIMIT,
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.getStockId()
                )

                val response = cashierApi.getProducts(request)
                if (response.products?.isNotEmpty() == true) {
                    productsDao.insert(response.products.toDomain())
                    newOffset += PRODUCTS_LIMIT
                } else {
                    productsLoaded = true
                }
            }
            Timber.d("worker ProductsWorker success")
            Result.success()
        } catch (e: Throwable) {
            Timber.e(e)
            Result.failure()
        }
    }
}