package com.grappim.common.lce

sealed class Try<out Data> {
    object Loading : Try<Nothing>()
    object Empty : Try<Nothing>()
    object Initial : Try<Nothing>()
    data class Error(val exception: Throwable) : Try<Nothing>()
    data class Success<out Data>(val data: Data) : Try<Data>()

    override fun toString(): String {
        return super.toString()
    }
}