package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import java.math.BigDecimal

interface UpdateWaybillProductUseCase {
    suspend fun execute(
        params: UpdateWaybillProductParams
    ): Try<BigDecimal, Throwable>
}