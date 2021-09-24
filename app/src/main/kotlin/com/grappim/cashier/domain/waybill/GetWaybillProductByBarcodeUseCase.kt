package com.grappim.cashier.domain.waybill

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.domain.repository.WaybillRepository
import javax.inject.Inject

class GetWaybillProductByBarcodeUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend operator fun invoke(
        barcode: String,
        waybillId: Int
    ): Either<Throwable, WaybillProduct> {
        val params = GetWaybillProductByBarcodeParams(
            barcode = barcode,
            waybillId = waybillId
        )
        return waybillRepository.getWaybillProductByBarcode(params)
    }

    data class GetWaybillProductByBarcodeParams(
        val barcode: String,
        val waybillId: Int
    )
}