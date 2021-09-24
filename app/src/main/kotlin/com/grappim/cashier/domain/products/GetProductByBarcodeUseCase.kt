package com.grappim.cashier.domain.products

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.WaybillRepository
import javax.inject.Inject

class GetProductByBarcodeUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend operator fun invoke(
        barcode: String
    ): Either<Throwable, ProductEntity> {
        val params = GetProductByBarcodeParams(barcode)
        return waybillRepository.getProductByBarcode(params)
    }

    data class GetProductByBarcodeParams(
        val barcode: String
    )
}