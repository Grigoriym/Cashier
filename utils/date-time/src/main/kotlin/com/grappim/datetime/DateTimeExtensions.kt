package com.grappim.datetime

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
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

fun String.getZonedDateTimeWithFormatter(
    inUtc: Boolean = true,
    formatter: DateTimeFormatter
): ZonedDateTime {
    val ldt = LocalDateTime.parse(this, formatter)
    val zdt = ldt.atZone(DateTimeUtils.getZoneOffset(inUtc))
    return zdt
}

fun OffsetDateTime.toUtc(): OffsetDateTime =
    this.withOffsetSameInstant(DateTimeUtils.getZoneOffset(true))

fun OffsetDateTime.toTheLocalDateTime(): OffsetDateTime =
    this.withOffsetSameInstant(DateTimeUtils.getZoneOffset(false))

fun OffsetDateTime.getEpochMilli(): Long = this.toInstant().toEpochMilli()

fun OffsetDateTime.getNanoOfSecond(): Long = this.getLong(ChronoField.NANO_OF_SECOND)

fun String.getZonedDateTime(
    inUtc: Boolean = false
): ZonedDateTime =
    ZonedDateTime.parse(this)
        .withZoneSameInstant(DateTimeUtils.getZoneOffset(inUtc))

fun ZonedDateTime.toTheLocalDateTime(): ZonedDateTime =
    this.withZoneSameInstant(DateTimeUtils.getZoneOffset(false))

fun ZonedDateTime.getEpochMilli(): Long = this.toInstant().toEpochMilli()

fun ZonedDateTime.getNanoOfSecond(): Long = this.getLong(ChronoField.NANO_OF_SECOND)
