package com.grappim.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logE
import com.grappim.network.api.WaybillApi
import com.grappim.network.model.waybill.FilterWaybillsRequestDTO
import com.grappim.network.model.waybill.WaybillDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetWaybillPagingSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val generalStorage: GeneralStorage,
    private val waybillApi: WaybillApi
) : PagingSource<Int, WaybillDTO>() {
    override fun getRefreshKey(state: PagingState<Int, WaybillDTO>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WaybillDTO> =
        withContext(ioDispatcher) {
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
                logE(e)
                LoadResult.Error(e)
            }
        }
}