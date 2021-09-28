package com.grappim.domain.interactor.waybill

import androidx.paging.PagingData
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillListPagingUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    operator fun invoke(): Flow<PagingData<Waybill>> =
        waybillRepository.getAcceptanceListPaging()

}