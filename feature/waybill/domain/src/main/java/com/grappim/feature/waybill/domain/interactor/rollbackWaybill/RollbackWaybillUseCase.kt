package com.grappim.feature.waybill.domain.interactor.rollbackWaybill

import com.grappim.cashier.common.lce.VoidTry
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import javax.inject.Inject

class RollbackWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend fun execute(params: RollbackWaybillParams): VoidTry<Throwable> =
        waybillRepository.rollbackWaybill(params)
}
