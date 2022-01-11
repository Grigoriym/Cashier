package com.grappim.date_time

import dagger.Module
import dagger.Provides
import java.time.format.DateTimeFormatter
import javax.inject.Qualifier

@Qualifier
annotation class DateTimeStandard

@Qualifier
annotation class DateStandard

@Qualifier
annotation class DateTimeIsoInstant

@Module
class DateTimeModule {

    companion object {
        private const val DATE_TIME_PATTERN_STANDARD = "dd.MM.yyyy HH:mm"

        private const val DATE_PATTERN_STANDARD = "dd.MM.yyyy"
    }

    @[Provides DateTimeStandard]
    fun provideDateTimeStandard(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_STANDARD)

    @[Provides DateStandard]
    fun provideDateStandard(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_PATTERN_STANDARD)

    @[Provides DateTimeIsoInstant]
    fun provideIsoInstant(): DateTimeFormatter =
        DateTimeFormatter.ISO_INSTANT

}