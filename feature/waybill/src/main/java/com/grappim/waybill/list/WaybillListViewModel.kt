package com.grappim.waybill.list

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.grappim.core.SingleLiveEvent
import com.grappim.date_time.DateStandard
import com.grappim.date_time.getOffsetDateTimeFromString
import com.grappim.domain.base.Result
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.waybill.CreateWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillListPagingUseCase
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.Navigator
import com.grappim.waybill.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WaybillListViewModel @Inject constructor(
    getWaybillListPagingUseCase: GetWaybillListPagingUseCase,
    private val createWaybillUseCase: CreateWaybillUseCase,
    private val navigator: Navigator,
    private val waybillLocalRepository: WaybillLocalRepository,
    @DateStandard private val df: DateTimeFormatter
) : ViewModel() {

    private val _waybill = SingleLiveEvent<Result<Waybill>>()
    val waybill: LiveData<Result<Waybill>>
        get() = _waybill

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _searchText = MutableStateFlow<String>(
        ""
    )
    val searchText: StateFlow<String>
        get() = _searchText.asStateFlow()

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun refresh() {
        viewModelScope.launch {

        }
    }

    val acceptances: Flow<PagingData<PagingDataModel<Waybill>>> =
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

    @MainThread
    fun createDraftWaybill() {
        viewModelScope.launch {
            createWaybillUseCase(withoutParams())
                .collect {
                    _waybill.value = it
                    when (it) {
                        is Result.Success -> {
                            showDetails(it.data)
                        }
                    }
                }
        }
    }

    fun showDetails(waybill: Waybill) {
        waybillLocalRepository.setWaybill(waybill)
        navigator.navigate(R.id.action_waybill_to_waybillDetails)
    }

}