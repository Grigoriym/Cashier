package com.grappim.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showOrGone(show: Boolean) {
    if (show) this.show() else this.hide()
}

fun View.showOrInvisible(show: Boolean) {
    if (show) this.show() else this.invisible()
}

fun View.disable() {
    this.isClickable = false
    this.isEnabled = false
}

fun View.enable() {
    this.isClickable = true
    this.isEnabled = true
}

fun View.enableOrDisable(value: Boolean) {
    if (value) {
        this.enable()
    } else {
        this.disable()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeOnClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
