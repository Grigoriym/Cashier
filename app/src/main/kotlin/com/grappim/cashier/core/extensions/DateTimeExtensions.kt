package com.grappim.cashier.core.extensions

import com.grappim.cashier.core.utils.DateTimeUtils
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField

fun String.getOffsetDateTimeFromString(
    inUtc: Boolean = false
): OffsetDateTime =
    OffsetDateTime
        .parse(this)
        .withOffsetSameInstant(DateTimeUtils.getZoneOffset(inUtc))

fun String.getOffsetDateTimeWithFormatter(
    inUtc: Boolean = true,
    formatter: DateTimeFormatter
): OffsetDateTime {
    val ldt = LocalDateTime.parse(this, formatter)
    val zdt = ldt.atZone(DateTimeUtils.getZoneOffset(inUtc))
    return zdt.toOffsetDateTime()
}

fun OffsetDateTime.toUtc(): OffsetDateTime =
    this.withOffsetSameInstant(DateTimeUtils.getZoneOffset(true))

fun OffsetDateTime.toTheLocalDateTime(): OffsetDateTime =
    this.withOffsetSameInstant(DateTimeUtils.getZoneOffset(false))

fun OffsetDateTime.getEpochMilli(): Long = this.toInstant().toEpochMilli()

fun OffsetDateTime.getNanoOfSecond(): Long = this.getLong(ChronoField.NANO_OF_SECOND)
