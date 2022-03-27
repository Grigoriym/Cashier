package com.grappim.cashier.di.app

import com.grappim.cashier.analytics.CrashesAnalyticsImpl
import com.grappim.domain.analytics.CrashesAnalytics
import dagger.Binds
import dagger.Module

@Module
interface AnalyticsBindsModule {

    @Binds
    fun bindCrashAnalytics(
        crashesAnalyticsImpl: CrashesAnalyticsImpl
    ): CrashesAnalytics
}