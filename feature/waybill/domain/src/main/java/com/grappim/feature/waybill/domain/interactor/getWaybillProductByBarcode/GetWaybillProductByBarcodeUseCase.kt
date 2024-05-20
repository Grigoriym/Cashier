package com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode

import com.grappim.cashier.common.lce.Try
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import javax.inject.Inject

class GetWaybillProductByBarcodeUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend fun execute(params: GetWaybillProductByBarcodeParams): Try<WaybillProduct, Throwable> =
        waybillRepository.getWaybillProductByBarcode(params)
}
