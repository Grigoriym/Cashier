package com.grappim.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.grappim.logger.logD
import com.grappim.network.BuildConfig
import com.grappim.network.authenticators.TokenAuthenticator
import com.grappim.network.interceptors.AuthTokenInterceptor
import com.grappim.network.interceptors.ErrorMappingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        gson: Gson
    ): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Provides
    @Singleton
    fun provideCashierRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit =
        builder.baseUrl(BuildConfig.CASHIER_API)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            logD("API", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        errorMappingInterceptor: ErrorMappingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        authTokenInterceptor: AuthTokenInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(errorMappingInterceptor)
            .addInterceptor(authTokenInterceptor)
            .authenticator(tokenAuthenticator)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                    addInterceptor(chuckerInterceptor)
                }
            }
            .build()

    @Singleton
    @Provides
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