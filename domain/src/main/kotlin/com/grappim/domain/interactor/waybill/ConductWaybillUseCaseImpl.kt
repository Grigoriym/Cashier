package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.repository.WaybillRepository
import javax.inject.Inject

class ConductWaybillUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : ConductWaybillUseCase {

    override suspend fun execute(
        params: ConductWaybillParams
    ): Try<Unit, Throwable> =
        waybillRepository.conductWaybill(params)
}