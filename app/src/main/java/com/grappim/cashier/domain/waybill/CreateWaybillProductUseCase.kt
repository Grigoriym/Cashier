package com.grappim.cashier.domain.waybill

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.domain.repository.WaybillRepository
import java.math.BigDecimal
import javax.inject.Inject

class CreateWaybillProductUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository
) {

    suspend operator fun invoke(
        waybillId: Int,
        barcode: String,
        name: String,
        purchasePrice: BigDecimal,
        sellingPrice: BigDecimal,
        amount: BigDecimal,
        productId: Long
    ): Either<Throwable, BigDecimal> {
        val params = CreateWaybillProductParams(
            waybillId = waybillId,
            barcode = barcode,
            name = name,
            purchasePrice = purchasePrice,
            sellingPrice = sellingPrice,
            amount = amount,
            productId = productId
        )
        return waybillRepository.createWaybillProduct(params)
    }

    data class CreateWaybillProductParams(
        val waybillId: Int,
        val barcode: String,
        val name: String,
        val purchasePrice: BigDecimal,
        val sellingPrice: BigDecimal,
        val amount: BigDecimal,
        val productId: Long
    )
}