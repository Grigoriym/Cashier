package com.grappim.common.lce

sealed class Try<out S, out E> {

    data class Success<out S>(val result: S) : Try<S, Nothing>()

    data class Error<out E>(val result: E) : Try<Nothing, E>()
}

typealias VoidTry<E> = Try<Unit, E>