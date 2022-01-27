package com.grappim.waybill.ui.list.ui.viewmodel

import androidx.paging.PagingData
import com.grappim.core.BaseViewModel
import com.grappim.domain.model.waybill.Waybill
import com.grappim.waybill.model.PagingDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillListViewModel : BaseViewModel() {

    abstract val isRefreshing: StateFlow<Boolean>
    abstract val searchText: StateFlow<String>
    abstract val acceptances: Flow<PagingData<PagingDataModel<Waybill>>>

    abstract fun setSearchText(text: String)
    abstract fun refresh()
    abstract fun createDraftWaybill()

    abstract fun onBackPressed()
    abstract fun showDetails(waybill: Waybill)
}