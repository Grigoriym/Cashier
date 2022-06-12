package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.VoidTry

interface RollbackWaybillUseCase {
    suspend fun execute(
        params: RollbackWaybillParams
    ): VoidTry<Throwable>
}