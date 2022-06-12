package com.grappim.waybill.ui.details.ui.viewmodel

import androidx.paging.PagingData
import com.grappim.core.base.BaseViewModel
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillDetailsViewModel : BaseViewModel() {

    abstract val comment: StateFlow<String>
    abstract val actualDate: StateFlow<String>
    abstract val products: Flow<PagingData<WaybillProduct>>

    abstract fun setActualDate(date: String)
    abstract fun setComment(text: String)
    abstract fun updateWaybill(waybill: Waybill)
    abstract fun showScanner()
    abstract fun showWaybillProduct(waybillProduct: WaybillProduct)
    abstract fun showSearchProducts()
}