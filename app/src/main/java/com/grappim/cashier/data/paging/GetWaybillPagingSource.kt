package com.grappim.cashier.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.cashier.api.WaybillApi
import com.grappim.cashier.core.executor.CoroutineContextProvider
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.data.remote.model.waybill.FilterWaybillsRequestDTO
import com.grappim.cashier.data.remote.model.waybill.WaybillDTO
import kotlinx.coroutines.withContext
import timber.log.Timber

class GetWaybillPagingSource(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val generalStorage: GeneralStorage,
    private val waybillApi: WaybillApi
) : PagingSource<Int, WaybillDTO>() {
    override fun getRefreshKey(state: PagingState<Int, WaybillDTO>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WaybillDTO> =
        withContext(coroutineContextProvider.io) {
            return@withContext try {
                val nextOffset = params.key ?: 0
                val request = FilterWaybillsRequestDTO(
                    limit = params.loadSize,
                    merchantId = generalStorage.getMerchantId(),
                    offset = nextOffset,
                    stockId = generalStorage.getStockId(),
                    waybillType = "inwaybill"
                )
                val waybills = waybillApi.filterWaybills(
                    request
                ).waybills ?: listOf()
                val newNextOffset: Int? = if (waybills.isEmpty()) {
                    null
                } else {
                    nextOffset + params.loadSize
                }
                LoadResult.Page(
                    data = waybills,
                    prevKey = null,
                    nextKey = newNextOffset
                )
            } catch (e: Throwable) {
                Timber.e(e)
                LoadResult.Error(e)
            }
        }
}