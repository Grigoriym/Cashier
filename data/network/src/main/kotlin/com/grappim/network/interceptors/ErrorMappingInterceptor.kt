package com.grappim.network.interceptors

import com.grappim.common.di.AppScope
import com.grappim.domain.model.base.BaseApiError
import com.grappim.domain.model.exception.NetworkException
import com.grappim.logger.logE
import com.grappim.network.model.base.BaseApiErrorDTO
import com.grappim.network.utils.NetworkHandler
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@AppScope
class ErrorMappingInterceptor @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val json: Json
) : Interceptor {

    @Suppress("SwallowedException", "TooGenericExceptionCaught")
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
            logE(throwable)
            throw throwable.mapNetworkException()
        }

    private fun mapErrorBodyToException(response: Response): Throwable {
        val responseBody = requireNotNull(response.body.string())
        val dto = json.decodeFromString<BaseApiErrorDTO>(responseBody)
        val baseApiError = BaseApiError(
            system = dto.system,
            status = dto.status,
            statusCode = dto.statusCode,
            message = dto.message,
            developerMessage = dto.developerMessage
        )
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
