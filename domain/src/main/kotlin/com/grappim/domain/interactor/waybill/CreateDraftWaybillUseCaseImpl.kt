package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.WaybillRepository
import javax.inject.Inject

class CreateDraftWaybillUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : CreateDraftWaybillUseCase {
    override suspend fun createDraftWaybill(): Try<Waybill, Throwable> =
        waybillRepository.createDraftWaybill()
}