package com.grappim.core.utils

import android.content.Context
import androidx.annotation.StringRes
import com.grappim.common.di.ActivityContext
import com.grappim.common.di.ActivityScope
import com.grappim.common.di.AppScope
import com.grappim.common.di.ApplicationContext
import javax.inject.Inject

@ActivityScope
class ResourceManager @Inject constructor(
    @ActivityContext private val activityContext: Context
) {

    fun getString(@StringRes res: Int): String =
        activityContext.getString(res)
}