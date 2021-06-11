package com.grappim.cashier.core.utils

import com.grappim.cashier.core.extensions.bigDecimalHundred
import com.grappim.cashier.core.extensions.bigDecimalZero
import com.grappim.cashier.core.extensions.mcEven
import java.math.BigDecimal
import javax.inject.Inject

class PriceCalculationsUtils @Inject constructor(

) {

    fun calculateOnChangingMarkup(
        purchasePrice: BigDecimal,
        markup: BigDecimal
    ): BigDecimal =
        purchasePrice.add(
            purchasePrice
                .multiply(markup, mcEven())
                .divide(bigDecimalHundred())
        )

    fun calculateOnChangingSellingPrice(
        sellingPrice: BigDecimal,
        purchasePrice: BigDecimal,
        doOnValid: (markup: BigDecimal) -> Unit
    ) {
        if (purchasePrice != bigDecimalZero()) {
            val markup = calculateMarkup(
                sellingPrice,
                purchasePrice
            )
            doOnValid.invoke(markup)
        }
    }

    private fun calculateMarkup(
        sellingPrice: BigDecimal,
        purchasePrice: BigDecimal,
    ): BigDecimal =
        ((sellingPrice.subtract(purchasePrice))
            .divide(purchasePrice, mcEven())
            ).multiply(bigDecimalHundred())

}