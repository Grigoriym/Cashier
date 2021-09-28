package com.grappim.domain.base

sealed class Result<out Data> {
    object Loading : Result<Nothing>()
    object Empty : Result<Nothing>()
    object Initial : Result<Nothing>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data class Success<out Data>(val data: Data) : Result<Data>()
}