package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.repository.WaybillRepository
import java.math.BigDecimal
import javax.inject.Inject

class CreateWaybillProductUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : CreateWaybillProductUseCase {

    override suspend fun execute(
        params: CreateWaybillProductParams
    ): Try<BigDecimal, Throwable> =
        waybillRepository.createWaybillProduct(params)
}