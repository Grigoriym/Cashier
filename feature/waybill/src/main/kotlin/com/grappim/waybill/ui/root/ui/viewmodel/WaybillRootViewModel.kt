package com.grappim.waybill.ui.root.ui.viewmodel

import com.grappim.core.BaseViewModel
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillRootViewModel : BaseViewModel() {

    abstract val waybillFlow: StateFlow<Waybill>

    abstract fun onBackPressed()
    abstract fun showSearchProducts()
    abstract fun showWaybillProduct(waybillProduct: WaybillProduct)
}