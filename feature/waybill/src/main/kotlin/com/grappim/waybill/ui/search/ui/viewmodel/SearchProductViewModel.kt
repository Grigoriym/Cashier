package com.grappim.waybill.ui.search.ui.viewmodel

import com.grappim.common.lce.Try
import com.grappim.core.BaseViewModel
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.StateFlow

abstract class SearchProductViewModel : BaseViewModel() {

    abstract val searchText: StateFlow<String>
    abstract val productsFlow: StateFlow<List<Product>>
    abstract val waybillProduct: StateFlow<Try<WaybillProduct>>

    abstract fun setSearchText(text: String)
    abstract fun onBackPressed()
    abstract fun checkProductInWaybill(product: Product)
}