package com.grappim.datetime

import java.time.OffsetDateTime
import java.time.ZoneOffset

object DateTimeUtils {

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
