package com.grappim.cashier.domain.waybill

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.domain.repository.WaybillRepository
import javax.inject.Inject

class CreateWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend operator fun invoke(): Either<Throwable, Waybill> =
        waybillRepository.createDraftWaybill()
}