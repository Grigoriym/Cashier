package com.grappim.core.utils

import android.content.Context
import androidx.annotation.StringRes
import com.grappim.common.di.AppScope
import com.grappim.common.di.ApplicationContext
import javax.inject.Inject

@AppScope
class ResourceManager @Inject constructor(
    @ApplicationContext private val activityContext: Context
) {

    fun getString(@StringRes res: Int): String =
        activityContext.getString(res)
}