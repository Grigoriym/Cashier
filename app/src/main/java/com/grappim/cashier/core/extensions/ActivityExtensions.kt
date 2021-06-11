package com.grappim.cashier.core.extensions

import android.app.Activity
import android.view.View

fun Activity.hideKeyboard2() {
    hideKeyboard(currentFocus ?: View(this))
}
