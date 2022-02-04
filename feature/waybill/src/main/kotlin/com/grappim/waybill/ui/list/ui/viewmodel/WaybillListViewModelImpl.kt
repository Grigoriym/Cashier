package com.grappim.waybill.ui.list.ui.viewmodel

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.date_time.DateStandard
import com.grappim.date_time.getOffsetDateTimeFromString
import com.grappim.domain.interactor.waybill.CreateWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillListPagingUseCase
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.BundleArgsKeys
import com.grappim.waybill.model.PagingDataModel
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WaybillListViewModelImpl @Inject constructor(
    getWaybillListPagingUseCase: GetWaybillListPagingUseCase,
    private val createWaybillUseCase: CreateWaybillUseCase,
    private val waybillLocalRepository: WaybillLocalRepository,
    private val waybillScreenNavigator: WaybillScreenNavigator,
    @DateStandard private val df: DateTimeFormatter
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
                        after.item.updatedOn.getOffsetDateTimeFromString()
                    )
                    if (before == null) {
                        return@insertSeparators PagingDataModel.Separator(
                            afterDate
                        )
                    }
                    val beforeDate = df.format(
                        before.item.updatedOn.getOffsetDateTimeFromString()
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

    override fun refresh() {
        viewModelScope.launch {

        }
    }

    override fun onBackPressed() {
        waybillScreenNavigator.goBack()
    }

    @MainThread
    override fun createDraftWaybill() {
        viewModelScope.launch {
            createWaybillUseCase(withoutParams())
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Success -> {
                            showDetails(it.data)
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }

    override fun showDetails(waybill: Waybill) {
        waybillLocalRepository.setWaybill(waybill)
        val args = Bundle(1).apply {
            putSerializable(BundleArgsKeys.ARG_KEY_WAYBILL, waybill)
        }
        waybillScreenNavigator.goToWaybillDetails(args)
    }

}