package com.grappim.cashier.core.network

import com.google.gson.Gson
import com.grappim.cashier.api.BaseApiError
import com.grappim.cashier.core.exception.NetworkException
import com.grappim.cashier.core.platform.NetworkHandler
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorMappingInterceptor @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val gson: Gson
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        try {
            if (!networkHandler.isConnected) throw NetworkException(NetworkException.ERROR_NO_INTERNET)
            val request = chain.request()
            val response = chain.proceed(request)
            if (response.isSuccessful) {
                response
            } else {
                throw mapErrorBodyToException(response)
            }

        } catch (throwable: Throwable) {
            throw throwable.mapNetworkException()
        }

    private fun mapErrorBodyToException(response: Response): Throwable {
        val baseApiError = gson.fromJson(response.body?.string(), BaseApiError::class.java)
        return NetworkException(
            errorCode = NetworkException.ERROR_API,
            apiError = baseApiError,
            request = "[Request] ${response.request.method} ${response.request.url}"
        )
    }

    private fun Throwable.mapNetworkException(): Throwable =
        when (this) {
            is NetworkException -> this
            is SocketTimeoutException -> NetworkException(NetworkException.ERROR_TIMEOUT, this)
            is UnknownHostException -> NetworkException(NetworkException.ERROR_HOST_NOT_FOUND, this)
            is IOException -> NetworkException(NetworkException.ERROR_NETWORK_IO, this)
            else -> NetworkException(NetworkException.ERROR_UNDEFINED, this)
        }

}