package com.grappim.feature.waybill.domain.interactor.createWaybillProduct

import com.grappim.common.lce.Try
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import java.math.BigDecimal
import javax.inject.Inject

class CreateWaybillProductUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend fun execute(
        params: CreateWaybillProductParams
    ): Try<BigDecimal, Throwable> =
        waybillRepository.createWaybillProduct(params)
}
