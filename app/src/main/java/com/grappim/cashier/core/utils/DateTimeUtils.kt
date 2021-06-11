package com.grappim.cashier.core.utils

import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    private const val DATE_TIME_PATTERN_STANDARD = "dd.MM.yyyy HH:mm"

    private const val DATE_PATTERN_STANDARD = "dd.MM.yyyy"

    private const val DATE_TIME_PATTERN_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"

    fun getDateTimePatternStandard(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_STANDARD)

    fun getDateTimeFormatterForFull(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_FULL)

    fun getNowFullDate(): String =
        getDateTimeFormatterForFull().format(getNowOffsetDateTime(true))

    fun getDatePatternStandard(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_PATTERN_STANDARD)

    fun getNowOffsetDateTime(
        inUtc: Boolean = false
    ): OffsetDateTime =
        OffsetDateTime.now(getZoneOffset(inUtc))

    fun getZoneOffset(inUtc: Boolean): ZoneOffset {
        return if (inUtc) {
            ZoneOffset.UTC
        } else {
            getCurrentZoneOffset()
        }
    }

    private fun getCurrentZoneOffset(): ZoneOffset = OffsetDateTime.now().offset

}