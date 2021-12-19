package com.grappim.core.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManager @Inject constructor(
    @ApplicationContext private val activityContext: Context
) {

    fun getString(@StringRes res: Int): String =
        activityContext.getString(res)
}