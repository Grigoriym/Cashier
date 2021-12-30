package com.grappim.date_time

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Deprecated(
    message = "remove it"
)
object DateTimeUtils {
    private const val DATE_TIME_PATTERN_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"

    @Deprecated("remove it")
    fun getDateTimeFormatterForFull(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_FULL)

    @Deprecated("remove it")
    fun getNowFullDate(): String =
        getDateTimeFormatterForFull().format(getNowOffsetDateTime(true))

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