package com.grappim.cashier.core.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.grappim.cashier.R
import com.grappim.cashier.core.exception.NetworkException

fun Context.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showToast(message: String?, long: Boolean = true) {
    val duration = when (long) {
        true -> Toast.LENGTH_LONG
        else -> Toast.LENGTH_SHORT
    }
    Toast.makeText(this, message, duration).show()
}

fun Context.getErrorMessage(throwable: Throwable): String {
    return if (throwable is NetworkException) {
        when (throwable.errorCode) {
            NetworkException.ERROR_API -> {
                throwable.apiError?.message ?: getString(R.string.error_something_has_gone_wrong)
            }
            NetworkException.ERROR_ON_REFRESH -> getString(R.string.auth_error_refresh_token_not_passed)
            NetworkException.ERROR_NO_INTERNET -> getString(R.string.error_no_internet_connection)
            NetworkException.ERROR_HOST_NOT_FOUND -> getString(R.string.error_host_not_found)
            NetworkException.ERROR_TIMEOUT -> getString(R.string.timeout_exceeded)
            NetworkException.ERROR_NETWORK_IO -> getString(R.string.connection_failed)
            NetworkException.ERROR_UNDEFINED -> getString(R.string.request_failed)
            else -> getString(R.string.error_something_has_gone_wrong)
        }
    } else {
        throwable.message.toString()
    }
}
