package com.grappim.waybill.ui.list.ui.viewmodel

import androidx.paging.PagingData
import com.grappim.core.base.BaseViewModel
import com.grappim.core.base.BaseViewModel2
import com.grappim.domain.model.waybill.Waybill
import com.grappim.waybill.model.PagingDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillListViewModel : BaseViewModel2() {

    abstract val isRefreshing: StateFlow<Boolean>
    abstract val searchText: StateFlow<String>
    abstract val acceptances: Flow<PagingData<PagingDataModel<Waybill>>>

    abstract fun setSearchText(text: String)
    abstract fun createDraftWaybill()

    abstract fun showDetails(waybill: Waybill)
}