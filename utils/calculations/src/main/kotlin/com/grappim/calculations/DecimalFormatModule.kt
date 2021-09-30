package com.grappim.calculations

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class DecimalFormatSimple

@Qualifier
annotation class DecimalFormatFullOptionalDecimal

@Module
@InstallIn(SingletonComponent::class)
object DecimalFormatModule {

    private const val PATTERN_SIMPLE_DECIMAL = "###.##"
    private const val PATTERN_FULL_OPTIONAL_DECIMAL = "###,###.##"

    @Provides
    @Singleton
    fun provideDecimalFormatSymbols(): DecimalFormatSymbols =
        DecimalFormatSymbols()

    @Provides
    @Singleton
    @DecimalFormatSimple
    fun provideSimpleDecimal(
        decimalFormatSymbols: DecimalFormatSymbols
    ): DecimalFormat {
        val dfs = decimalFormatSymbols.apply {
            decimalSeparator = '.'
        }
        return DecimalFormat(PATTERN_SIMPLE_DECIMAL, dfs)
    }

    @Provides
    @Singleton
    @DecimalFormatFullOptionalDecimal
    fun getFullOptionalDecimal(
        decimalFormatSymbols: DecimalFormatSymbols
    ): DecimalFormat {
        val dfs = decimalFormatSymbols.apply {
            decimalSeparator = '.'
            groupingSeparator = ' '
        }
        return DecimalFormat(PATTERN_FULL_OPTIONAL_DECIMAL, dfs)
    }
}