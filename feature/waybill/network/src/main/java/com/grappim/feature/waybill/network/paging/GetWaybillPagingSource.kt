package com.grappim.feature.waybill.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.waybill.network.api.WaybillApi
import com.grappim.feature.waybill.network.model.FilterWaybillsRequestDTO
import com.grappim.feature.waybill.network.model.WaybillDTO
import com.grappim.logger.logE
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetWaybillPagingSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val generalStorage: GeneralStorage,
    private val waybillApi: WaybillApi
) : PagingSource<Int, WaybillDTO>() {
    override fun getRefreshKey(state: PagingState<Int, WaybillDTO>): Int? = null

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WaybillDTO> =
        withContext(ioDispatcher) {
            return@withContext try {
                val nextOffset = params.key ?: 0
                val request = FilterWaybillsRequestDTO(
                    limit = params.loadSize,
                    merchantId = generalStorage.getMerchantId(),
                    offset = nextOffset,
                    stockId = generalStorage.stockId,
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
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                logE(e)
                LoadResult.Error(e)
            }
        }
}
