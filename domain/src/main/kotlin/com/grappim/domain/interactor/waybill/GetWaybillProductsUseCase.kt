package com.grappim.domain.interactor.waybill

import androidx.paging.PagingData
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.Flow

interface GetWaybillProductsUseCase {
    fun execute(
        params: GetWaybillProductsParams
    ): Flow<PagingData<WaybillProduct>>
}