package com.grappim.cashier.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.cashier.api.WaybillApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.model.waybill.WaybillProductDTO
import com.grappim.cashier.data.remote.model.waybill.WaybillProductsRequestDTO
import kotlinx.coroutines.withContext
import timber.log.Timber

class GetWaybillProductsPagingSource constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val generalStorage: GeneralStorage,
    private val waybillApi: WaybillApi,
    private val waybillId: Int
) : PagingSource<Int, WaybillProductDTO>() {
    override fun getRefreshKey(state: PagingState<Int, WaybillProductDTO>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WaybillProductDTO> =
        withContext(coroutineContextProvider.io) {
            return@withContext try {
                val nextOffset = params.key ?: 0
                val request = WaybillProductsRequestDTO(
                    limit = params.loadSize,
                    offset = nextOffset,
                    waybillId = waybillId
                )
                val waybillProducts = waybillApi.getWaybillProductList(
                    request
                ).products ?: listOf()
                val newNextOffset: Int? = if (waybillProducts.isEmpty()) {
                    null
                } else {
                    nextOffset + params.loadSize
                }
                LoadResult.Page(
                    data = waybillProducts,
                    prevKey = null,
                    nextKey = newNextOffset
                )
            } catch (e: Throwable) {
                Timber.e(e)
                LoadResult.Error(e)
            }
        }
}