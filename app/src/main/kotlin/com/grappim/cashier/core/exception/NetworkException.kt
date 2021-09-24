package com.grappim.cashier.core.exception

import com.grappim.cashier.api.BaseApiError
import java.io.IOException

class NetworkException(
    val errorCode: Int,
    val throwable: Throwable? = null,
    val apiError: BaseApiError? = null,
    val request: String? = ""
) : IOException() {

    companion object {
        const val ERROR_API = -1
        const val ERROR_NO_INTERNET = -2
        const val ERROR_HOST_NOT_FOUND = -3
        const val ERROR_TIMEOUT = -4
        const val ERROR_NETWORK_IO = -5
        const val ERROR_UNDEFINED = -6
        const val ERROR_ON_REFRESH = -7
    }
}