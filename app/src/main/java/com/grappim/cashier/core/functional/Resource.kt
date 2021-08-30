package com.grappim.cashier.core.functional

sealed class Resource<out Data> {
    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()
    object Initial : Resource<Nothing>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    data class Success<out Data>(val data: Data) : Resource<Data>()
}

sealed class StatefulResource<out Data> {
    class Error(val exception: Throwable) : StatefulResource<Nothing>()
    object Loading : StatefulResource<Nothing>()
    object Initial : StatefulResource<Nothing>()
    class Data<out Data>(val data: Data) : StatefulResource<Data>()
}