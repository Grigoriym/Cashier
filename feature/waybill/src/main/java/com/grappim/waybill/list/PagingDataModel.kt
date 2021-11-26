package com.grappim.waybill.list

sealed class PagingDataModel<out T> {
    data class Item<out T>(val item: T) : PagingDataModel<T>()
    data class Separator(val text: String) : PagingDataModel<Nothing>()
}