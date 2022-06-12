package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try

interface ConductWaybillUseCase {
    suspend fun execute(
        params: ConductWaybillParams
    ): Try<Unit, Throwable>
}