package com.grappim.feature.waybill.presentation.ui.search.ui.viewmodel

import androidx.paging.PagingData
import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class SearchProductViewModel : BaseViewModel() {

    abstract val searchText: StateFlow<String>
    abstract val productsFlow: Flow<PagingData<Product>>

    abstract fun setSearchText(text: String)
    abstract fun checkProductInWaybill(product: Product)
}
