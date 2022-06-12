package com.grappim.waybill.ui.list.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.grappim.common.lce.Try
import com.grappim.date_time.DateStandard
import com.grappim.date_time.DateTimeIsoLocalDateTime
import com.grappim.domain.interactor.waybill.CreateDraftWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillListPagingUseCase
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.model.PagingDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WaybillListViewModelImpl @Inject constructor(
    getWaybillListPagingUseCase: GetWaybillListPagingUseCase,
    private val createDraftWaybillUseCase: CreateDraftWaybillUseCase,
    private val waybillLocalRepository: WaybillLocalRepository,
    @DateStandard private val df: DateTimeFormatter,
    @DateTimeIsoLocalDateTime val dtfIso: DateTimeFormatter
) : WaybillListViewModel() {

    override val isRefreshing = MutableStateFlow(false)

    override val searchText = MutableStateFlow<String>("")

    override val acceptances: Flow<PagingData<PagingDataModel<Waybill>>> =
        getWaybillListPagingUseCase.invoke()
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map {
                    PagingDataModel.Item(it)
                }
            }
            .map {
                it.insertSeparators { before, after ->
                    if (after == null) {
                        return@insertSeparators null
                    }
                    val afterDate = df.format(
                        dtfIso.parse(after.item.updatedOn)
                    )
                    if (before == null) {
                        return@insertSeparators PagingDataModel.Separator(
                            afterDate
                        )
                    }
                    val beforeDate = df.format(
                        dtfIso.parse(before.item.updatedOn)
                    )

                    if (beforeDate != afterDate) {
                        PagingDataModel.Separator(afterDate)
                    } else {
                        null
                    }
                }
            }

    override fun setSearchText(text: String) {
        searchText.value = text
    }

    @MainThread
    override fun createDraftWaybill() {
        viewModelScope.launch {
            _loading.value = true
            val result = createDraftWaybillUseCase.createDraftWaybill()
            _loading.value = false
            when (result) {
                is Try.Success -> {
                    showDetails(result.result)
                }
                is Try.Error -> {
                    _error.value = result.result
                }
            }
        }
    }

    override fun showDetails(waybill: Waybill) {
        waybillLocalRepository.setWaybill(waybill)
        flowRouter.goToWaybillDetails()
    }

}