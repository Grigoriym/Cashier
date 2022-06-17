package com.grappim.feature.waybill.presentation.ui.list.ui.viewmodel

import androidx.paging.PagingData
import com.grappim.core.base.BaseViewModel
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.presentation.model.PagingDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillListViewModel : BaseViewModel() {

    abstract val isRefreshing: StateFlow<Boolean>
    abstract val searchText: StateFlow<String>
    abstract val acceptances: Flow<PagingData<PagingDataModel<Waybill>>>

    abstract fun setSearchText(text: String)
    abstract fun createDraftWaybill()

    abstract fun showDetails(waybill: Waybill)
}