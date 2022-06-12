package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import java.math.BigDecimal

interface CreateWaybillProductUseCase {
    suspend fun execute(
        params: CreateWaybillProductParams
    ): Try<BigDecimal, Throwable>
}