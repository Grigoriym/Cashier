package com.grappim.date_time

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class DateTimeStandard

@Qualifier
annotation class DateStandard

@Qualifier
annotation class DateTimeIsoInstant

@Module
@InstallIn(SingletonComponent::class)
object DateTimeModule {

    private const val DATE_TIME_PATTERN_STANDARD = "dd.MM.yyyy HH:mm"

    private const val DATE_PATTERN_STANDARD = "dd.MM.yyyy"

    @DateTimeStandard
    @Provides
    @Singleton
    fun provideDateTimeStandard(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_STANDARD)

    @DateStandard
    @Provides
    @Singleton
    fun provideDateStandard(): DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_PATTERN_STANDARD)

    @DateTimeIsoInstant
    @Provides
    @Singleton
    fun provideIsoInstant(): DateTimeFormatter =
        DateTimeFormatter.ISO_INSTANT

}