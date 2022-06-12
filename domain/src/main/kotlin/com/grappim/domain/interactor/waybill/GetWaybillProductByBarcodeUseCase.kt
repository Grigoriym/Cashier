package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.WaybillProduct

interface GetWaybillProductByBarcodeUseCase {
    suspend fun execute(
        params: GetWaybillProductByBarcodeParams
    ): Try<WaybillProduct, Throwable>
}