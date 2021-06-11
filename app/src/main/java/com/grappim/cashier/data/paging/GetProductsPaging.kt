package com.grappim.cashier.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.cashier.api.CashierApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.data.remote.model.product.GetProductsRequestDTO
import com.grappim.cashier.di.modules.QualifierCashierApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetProductsPaging @AssistedInject constructor(
    @Assisted private val productFilter: ProductFilter,
    private val coroutineContextProvider: CoroutineContextProvider,
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val generalStorage: GeneralStorage,
) : PagingSource<Long, ProductEntity>() {

    @AssistedFactory
    interface Factory {
        fun create(productFilter: ProductFilter): GetProductsPaging
    }

    override fun getRefreshKey(state: PagingState<Long, ProductEntity>): Long? = null

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ProductEntity> =
        withContext(coroutineContextProvider.io) {
            try {
                val productsOffset = params.key ?: 0

                val request = GetProductsRequestDTO(
                    offset = productsOffset,
                    limit = params.loadSize.toLong(),
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.getStockId(),
                    name = productFilter.name
                )

                val response = cashierApi.getProducts(request)

//                val newOffset: Int? = if (response.isEmpty()) {
//                    null
//                } else {
//                    newOffset + params.loadSize
//                }

                LoadResult.Page(
                    data = listOf(),
                    prevKey = null,
                    nextKey = null
                )
            } catch (e: Throwable) {
                Timber.e(e)
                LoadResult.Error(e)
            }
        }
}

data class ProductFilter(
    val name: String
)