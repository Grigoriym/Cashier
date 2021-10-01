package com.grappim.extensions

import android.app.Activity
import androidx.core.view.WindowInsetsCompat.Type
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.openKeyboard() {
    WindowInsetsControllerCompat(window, window.decorView).show(Type.ime())
}

fun Activity.hideKeyboard() {
    WindowInsetsControllerCompat(window, window.decorView).hide(Type.ime())
}