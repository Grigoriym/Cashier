package com.grappim.feature.waybill.domain.interactor.updateWaybillProduct

import com.grappim.common.lce.Try
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import java.math.BigDecimal
import javax.inject.Inject

class UpdateWaybillProductUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend fun execute(
        params: UpdateWaybillProductParams
    ): Try<BigDecimal, Throwable> =
        waybillRepository.updateWaybillProduct(params)
}