package com.grappim.feature.waybill.presentation.model

sealed class PagingDataModel<out T> {
    data class Item<out T>(val item: T) : com.grappim.feature.waybill.presentation.model.PagingDataModel<T>()
    data class Separator(val text: String) : com.grappim.feature.waybill.presentation.model.PagingDataModel<Nothing>()
}
