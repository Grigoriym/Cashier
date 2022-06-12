package com.grappim.domain.interactor.waybill

import androidx.paging.PagingData
import com.grappim.domain.model.waybill.Waybill
import kotlinx.coroutines.flow.Flow

interface GetWaybillListPagingUseCase {
    fun invoke(): Flow<PagingData<Waybill>>
}