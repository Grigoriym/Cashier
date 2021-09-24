package com.grappim.cashier.data.remote

import com.grappim.cashier.core.functional.Either
import retrofit2.Call
import timber.log.Timber

open class BaseRepository {

    protected suspend fun <T : Any> apiCall(
        call: suspend () -> T
    ): Either<Throwable, T> =
        try {
            Either.Right(call.invoke())
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Either.Left(throwable)
        }

    protected fun <T : Any> apiCallSync(
        call: () -> Call<T>
    ): Either<Throwable, T> =
        try {
            Either.Right(call.invoke().execute().body()!!)
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Either.Left(throwable)
        }
}