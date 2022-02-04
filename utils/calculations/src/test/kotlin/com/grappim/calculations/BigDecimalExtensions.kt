package com.grappim.calculations

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionsTest {

    @Test
    fun `Big decimal string asBigDecimal`() {
        assertTrue("0".asBigDecimal().compareTo(BigDecimal.ZERO) == 0)
        assertTrue(".".asBigDecimal().compareTo(BigDecimal.ZERO) == 0)
        assertTrue("-".asBigDecimal().compareTo(BigDecimal.ZERO) == 0)

        assertFalse("0".asBigDecimal().compareTo(BigDecimal.ONE) == 0)

        assertTrue("0.00000".asBigDecimal().compareTo(BigDecimal.ZERO) == 0)

        assertTrue("0".asBigDecimal().compareTo(BigDecimal(0.0)) == 0)
        assertTrue("0".asBigDecimal().compareTo(bigDecimalZero()) == 0)
        assertTrue("0".asBigDecimal().compareTo(bigDecimalOne()) == -1)
    }

    @Test
    fun `Big decimal add`() {
        assertEquals(
            "100".asBigDecimal()
                .plus("51".asBigDecimal()).compareTo(BigDecimal(151)),
            0
        )
    }

    @Test
    fun `Big decimal is not equals zero`() {
        assertTrue(BigDecimal.ONE.isNotEqualsZero())
        assertTrue(BigDecimal.TEN.isNotEqualsZero())
        assertTrue(BigDecimal(1.0).isNotEqualsZero())
        assertTrue(BigDecimal(-0.1).isNotEqualsZero())
        assertTrue(BigDecimal(0.1).isNotEqualsZero())
        assertTrue(BigDecimal(0.2).isNotEqualsZero())
        assertTrue(BigDecimal("0.1").isNotEqualsZero())
        assertTrue(BigDecimal("-0.1").isNotEqualsZero())
    }

    @Test
    fun `Big decimal is equals zero`() {
        assertFalse(BigDecimal.ZERO.isNotEqualsZero())
        assertFalse("0".asBigDecimal().isNotEqualsZero())
        assertFalse(BigDecimal("0").isNotEqualsZero())
        assertFalse(BigDecimal(0.0).isNotEqualsZero())
        assertFalse(BigDecimal(0).isNotEqualsZero())
    }

}