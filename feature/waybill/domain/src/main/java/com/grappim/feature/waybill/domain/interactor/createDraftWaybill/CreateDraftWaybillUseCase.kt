package com.grappim.feature.waybill.domain.interactor.createDraftWaybill

import com.grappim.common.lce.Try
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import javax.inject.Inject

class CreateDraftWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {
    suspend fun createDraftWaybill(): Try<Waybill, Throwable> =
        waybillRepository.createDraftWaybill()
}
