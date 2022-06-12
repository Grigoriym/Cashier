package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.VoidTry
import com.grappim.domain.repository.WaybillRepository
import javax.inject.Inject

class RollbackWaybillUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : RollbackWaybillUseCase {

    override suspend fun execute(
        params: RollbackWaybillParams
    ): VoidTry<Throwable> =
        waybillRepository.rollbackWaybill(params)
}