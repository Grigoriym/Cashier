package com.grappim.cashier.di.modules

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

@Module
@InstallIn(SingletonComponent::class)
object DateTimeModule {

    private const val DATE_TIME_PATTERN_STANDARD = "dd.MM.yyyy HH:mm"

    private const val DATE_PATTERN_STANDARD = "dd.MM.yyyy"

    private const val DATE_TIME_PATTERN_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"

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

}