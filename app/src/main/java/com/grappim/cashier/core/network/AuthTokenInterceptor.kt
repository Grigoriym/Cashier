package com.grappim.cashier.core.network

import com.grappim.cashier.core.storage.GeneralStorage
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject
import javax.inject.Singleton

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestWithAuthToken

@Singleton
class AuthTokenInterceptor @Inject constructor(
    private val generalStorage: GeneralStorage
) : Interceptor {

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        val annotation = getAnnotation(request)
        if (annotation != null) {
            requestBuilder.putXownerAndAuthorization()
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun getAnnotation(request: Request): RequestWithAuthToken? =
        request.tag(Invocation::class.java)
            ?.method()?.getAnnotation(RequestWithAuthToken::class.java)

    private fun Request.Builder.putXownerAndAuthorization() {
        val token = generalStorage.getBearerAuthToken()
        this@putXownerAndAuthorization.header(
            AUTHORIZATION,
            token
        )
    }
}
