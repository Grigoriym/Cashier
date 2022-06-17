package com.grappim.feature.waybill.domain.interactor.getWaybillListPaging

import androidx.paging.PagingData
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillListPagingUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    operator fun invoke(): Flow<PagingData<Waybill>> =
        waybillRepository.getAcceptanceListPaging()

}