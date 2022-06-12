package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

class UpdateWaybillProductUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : UpdateWaybillProductUseCase {

    override suspend fun execute(
        params: UpdateWaybillProductParams
    ): Try<BigDecimal, Throwable> =
        waybillRepository.updateWaybillProduct(params)
}