package com.grappim.feature.waybill.domain.interactor.conductWaybill

import com.grappim.common.lce.Try
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import javax.inject.Inject

class ConductWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend fun execute(
        params: ConductWaybillParams
    ): Try<Unit, Throwable> =
        waybillRepository.conductWaybill(params)
}
