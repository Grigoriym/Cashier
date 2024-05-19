package com.grappim.calculations

import dagger.Module
import dagger.Provides
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Qualifier

@Qualifier
annotation class DecimalFormatSimple

@Qualifier
annotation class DecimalFormatFullOptionalDecimal

@Module
object DecimalFormatModule {

    private const val PATTERN_SIMPLE_DECIMAL = "###.##"
    private const val PATTERN_FULL_OPTIONAL_DECIMAL = "###,###.##"

    @[Provides]
    fun provideDecimalFormatSymbols(): DecimalFormatSymbols =
        DecimalFormatSymbols()

    @[Provides DecimalFormatSimple]
    fun provideSimpleDecimal(
        decimalFormatSymbols: DecimalFormatSymbols
    ): DecimalFormat {
        val dfs = decimalFormatSymbols.apply {
            decimalSeparator = '.'
        }
        return DecimalFormat(PATTERN_SIMPLE_DECIMAL, dfs)
    }

    @[Provides DecimalFormatFullOptionalDecimal]
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
