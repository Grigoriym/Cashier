package com.grappim.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.NetworkScope
import com.grappim.logger.logD
import com.grappim.network.authenticators.TokenAuthenticator
import com.grappim.network.di.configs.CashierApiUrlProvider
import com.grappim.network.di.configs.NetworkBuildConfigProvider
import com.grappim.network.di.configs.NetworkConfigsModule
import com.grappim.network.interceptors.AuthTokenInterceptor
import com.grappim.network.interceptors.ErrorMappingInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

@Module(
    includes = [
        NetworkConfigsModule::class
    ]
)
object NetworkModule {

    @[NetworkScope Provides]
    fun provideRetrofitBuilder(
        json: Json
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

    @[NetworkScope Provides]
    fun provideCashierRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        cashierApiUrlProvider: CashierApiUrlProvider
    ): Retrofit =
        builder.baseUrl(cashierApiUrlProvider.cashierApi)
            .client(okHttpClient)
            .build()

    @[NetworkScope Provides]
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            logD("API", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @[NetworkScope Provides]
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        errorMappingInterceptor: ErrorMappingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        authTokenInterceptor: AuthTokenInterceptor,
        tokenAuthenticator: TokenAuthenticator,
        networkBuildConfigProvider: NetworkBuildConfigProvider
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(errorMappingInterceptor)
            .addInterceptor(authTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .apply {
                if (networkBuildConfigProvider.debug) {
                    addInterceptor(loggingInterceptor)
                    addInterceptor(chuckerInterceptor)
                }
            }
            .build()

    @[NetworkScope Provides]
    fun provideChuckerInterceptor(
        @ApplicationContext appContext: Context
    ): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = appContext,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        return ChuckerInterceptor.Builder(context = appContext)
            .collector(collector = chuckerCollector)
            .maxContentLength(
                length = 250000L
            )
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(true)
            .build()
    }
}