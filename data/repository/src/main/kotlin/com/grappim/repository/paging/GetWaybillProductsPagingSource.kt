package com.grappim.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.grappim.logger.logE
import com.grappim.network.api.WaybillApi
import com.grappim.network.model.waybill.WaybillProductDTO
import com.grappim.network.model.waybill.WaybillProductsRequestDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetWaybillProductsPagingSource constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val waybillApi: WaybillApi,
    private val waybillId: Long
) : PagingSource<Int, WaybillProductDTO>() {
    override fun getRefreshKey(state: PagingState<Int, WaybillProductDTO>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WaybillProductDTO> =
        withContext(ioDispatcher) {
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
                logE(e)
                LoadResult.Error(e)
            }
        }
}