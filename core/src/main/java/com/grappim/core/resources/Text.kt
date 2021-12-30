package com.grappim.core.resources

import android.content.Context
import androidx.annotation.StringRes

sealed class Text {
    data class PlainText(
        val value: String
    ) : Text()

    data class ResText(
        @StringRes val resId: Int,
        val args: List<Any>? = null
    ) : Text()
}

fun Text.toCharSequence(context: Context): CharSequence =
    when (this) {
        is Text.PlainText -> value
        is Text.ResText -> {
            if (args == null) {
                context.getString(resId)
            } else {
                context.getString(resId, *args.toTypedArray())
            }
        }
    }
