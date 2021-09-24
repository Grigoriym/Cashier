package com.grappim.cashier.core.functional

import com.grappim.cashier.core.functional.Either.Left
import com.grappim.cashier.core.functional.Either.Right
import timber.log.Timber

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Unit): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

    fun either(fnL: (L) -> Any, fnR: () -> Unit): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR()
        }

    class Empty
}

inline fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }

inline fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> right(fn(b))
    }

inline fun <L, R> Either<L, R>.onSuccess(action: (R) -> Unit): Either<L, R> {
    if (this is Either.Right) action(b)
    return this
}

inline fun <L, R> Either<L, R>.onFailure(action: (L) -> Unit): Either<L, R> {
    if (this is Either.Left) {
        Timber.e("Some shit happened $a")
        action(a)
    }
    return this
}

inline fun <L, R, Z> Either<L, R>.zipWith(fn: (R) -> Either<L, Z>): Either<L, Pair<R, Z>> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> {
            fn(b).flatMap {
                Either.Right(Pair(b, it))
            }
        }
    }

inline fun <T, L, R> Either<L, R>.onFailureResumeNext(failure: (L) -> Either<T, R>): Either<T, R> =
    when (this) {
        is Either.Left -> failure(a)
        is Either.Right -> Either.Right(b)
    }