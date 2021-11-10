package com.grappim.calculations

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
        if (purchasePrice.isNotEqualsZero()) {
            val markup = calculateMarkup(
                sellingPrice,
                purchasePrice
            )
            doOnValid.invoke(markup)
        }
    }

    fun calculateOnChangingSellingPrice(
        sellingPrice: BigDecimal,
        purchasePrice: BigDecimal
    ): BigDecimal =
        if (purchasePrice.isNotEqualsZero()) {
            calculateMarkup(
                sellingPrice,
                purchasePrice
            )
        } else {
            bigDecimalZero()
        }

    private fun calculateMarkup(
        sellingPrice: BigDecimal,
        purchasePrice: BigDecimal,
    ): BigDecimal =
        ((sellingPrice.subtract(purchasePrice))
            .divide(purchasePrice, mcEven())
                ).multiply(bigDecimalHundred())

}