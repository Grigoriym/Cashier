package com.grappim.waybill.model

sealed class PagingDataModel<out T> {
    data class Item<out T>(val item: T) : PagingDataModel<T>()
    data class Separator(val text: String) : PagingDataModel<Nothing>()
}