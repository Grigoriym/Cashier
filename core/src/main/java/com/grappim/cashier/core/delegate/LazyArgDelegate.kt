package com.grappim.cashier.core.delegate

import android.app.Activity
import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.lazyArg(
    key: String,
    noinline builder: ((Any?) -> T)? = null
): Lazy<T> = lazy(mode = LazyThreadSafetyMode.NONE) {
    val value = arguments?.get(key)
    return@lazy if (builder != null) {
        builder.invoke(value)
    } else {
        value as T
    }
}

inline fun <reified T> Activity.lazyArg(key: String): Lazy<T> =
    lazy(mode = LazyThreadSafetyMode.NONE) {
        intent?.extras?.get(key) as T
    }
