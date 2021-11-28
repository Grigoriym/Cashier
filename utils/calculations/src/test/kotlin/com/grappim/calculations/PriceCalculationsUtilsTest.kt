package com.grappim.calculations

import org.junit.Assert.assertTrue
import org.junit.Test

class PriceCalculationsUtilsTest {

    @Test
    fun `Calculate markup`() {
        val utils = PriceCalculationsUtils()

        assertTrue(
            utils.calculateMarkup(
                sellingPrice = "100".asBigDecimal(),
                purchasePrice = "200".asBigDecimal()
            ).compareTo("-50".asBigDecimal()) == 0
        )

        assertTrue(
            utils.calculateMarkup(
                sellingPrice = "102".asBigDecimal(),
                purchasePrice = "100".asBigDecimal()
            ).compareTo("2".asBigDecimal()) == 0
        )
    }

}