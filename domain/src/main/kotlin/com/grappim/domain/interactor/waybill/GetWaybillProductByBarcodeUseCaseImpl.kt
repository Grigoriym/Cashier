package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.WaybillRepository
import javax.inject.Inject

class GetWaybillProductByBarcodeUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : GetWaybillProductByBarcodeUseCase {

    override suspend fun execute(
        params: GetWaybillProductByBarcodeParams
    ): Try<WaybillProduct, Throwable> =
        waybillRepository.getWaybillProductByBarcode(params)
}