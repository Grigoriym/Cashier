@file:Suppress("DEPRECATION")

package com.grappim.extensions

import android.content.Context
import android.os.Build
import android.widget.Toast
import com.grappim.domain.model.exception.NetworkException

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

fun Context.getAppVersion(): String {
    val manager = packageManager
    val info = manager?.getPackageInfo(
        packageName,
        0
    )
    val versionName = info?.versionName
    val versionNumber = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        info?.longVersionCode
    } else {
        info?.versionCode
    }
    return "$versionName $versionNumber"
}