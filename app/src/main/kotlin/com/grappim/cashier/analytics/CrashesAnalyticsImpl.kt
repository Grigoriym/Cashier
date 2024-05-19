package com.grappim.cashier.analytics

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.grappim.common.di.AppScope
import com.grappim.domain.analytics.CrashesAnalytics
import javax.inject.Inject

@AppScope
class CrashesAnalyticsImpl @Inject constructor(

) : CrashesAnalytics {

    override fun setUserId(id: String) {
        Firebase.crashlytics.setUserId(id)
    }

    override fun userName(name: String) {
        Firebase.crashlytics.log("userName: $name")
    }
}
