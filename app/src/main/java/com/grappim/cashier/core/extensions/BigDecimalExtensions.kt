package com.grappim.cashier.core.extensions

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

@Throws(NumberFormatException::class)
fun String?.asBigDecimal(scale: Int = 3): BigDecimal =
    when {
        this.isNullOrBlank() -> {
            BigDecimal("0").setScale(scale, RoundingMode.HALF_EVEN)
        }
        this == "." -> {
            BigDecimal("0.0").setScale(scale, RoundingMode.HALF_EVEN)
        }
        this == "-" -> {
            BigDecimal("0.0").setScale(scale, RoundingMode.HALF_EVEN)
        }
        else -> {
            BigDecimal(this).setScale(scale, RoundingMode.HALF_EVEN)
        }
    }

fun String?.asBigDecimalOrNull(scale: Int = 3): BigDecimal? =
    try {
        this?.asBigDecimal(scale)
    } catch (e: Exception) {
        null
    }

fun bigDecimalZero(scale: Int = 3): BigDecimal = "0".asBigDecimal(scale)

fun bigDecimalOne(scale: Int = 3): BigDecimal = "1".asBigDecimal(scale)

fun bigDecimalHundred(): BigDecimal = "100".asBigDecimal()

fun bigDecimalSignedOne(): BigDecimal = "-1".asBigDecimal()

fun mcEven(precision: Int = 9) = MathContext(precision, RoundingMode.HALF_EVEN)