package com.grappim.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.db.dao.ProductsDao
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logD
import com.grappim.logger.logE
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.mappers.products.ProductMapper
import com.grappim.network.model.products.GetProductsRequestDTO
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
    private val productsDao: ProductsDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productMapper: ProductMapper
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val PRODUCTS_LIMIT = 20L
    }

    @AssistedFactory
    interface Factory {
        fun create(
            appContext: Context,
            parameters: WorkerParameters
        ): ProductsWorker
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
                    stockId = generalStorage.stockId
                )

                val response = cashierApi.getProducts(request)
                val products = response.products
                if (products?.isNotEmpty() == true) {
                    val entities = productMapper.dtoToEntityList(products)
                    productsDao.insert(entities)
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