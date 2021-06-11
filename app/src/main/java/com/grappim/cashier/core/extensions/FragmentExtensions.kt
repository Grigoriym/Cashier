package com.grappim.cashier.core.extensions

import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard2() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.showToast(message: String?, long: Boolean = true) =
    requireContext().showToast(message, long)

fun Fragment.getErrorMessage(throwable: Throwable): String =
    requireContext().getErrorMessage(throwable)