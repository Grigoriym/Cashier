package com.grappim.waybill.ui.search.ui.viewmodel

import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.core.base.BaseViewModel2
import com.grappim.domain.model.product.Product
import com.grappim.domain.model.waybill.WaybillProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class SearchProductViewModel : BaseViewModel2() {

    abstract val searchText: StateFlow<String>
    abstract val productsFlow: Flow<PagingData<Product>>
    abstract val waybillProduct: StateFlow<Try<WaybillProduct>>

    abstract fun setSearchText(text: String)
    abstract fun checkProductInWaybill(product: Product)
}