package com.grappim.cashier.ui.waybill.list

import androidx.annotation.MainThread
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.grappim.cashier.core.extensions.getOffsetDateTimeFromString
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.domain.extension.withoutParams
import com.grappim.cashier.domain.waybill.CreateWaybillUseCase
import com.grappim.cashier.domain.waybill.GetWaybillListPagingUseCase
import com.grappim.cashier.domain.waybill.Waybill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaybillListViewModel @Inject constructor(
    getWaybillListPagingUseCase: GetWaybillListPagingUseCase,
    private val createWaybillUseCase: CreateWaybillUseCase
) : ViewModel() {

    private val _waybill = mutableStateOf<Resource<Waybill>>(
        Resource.Initial
    )
    val waybill: State<Resource<Waybill>>
        get() = _waybill

    private val df = DateTimeUtils.getDatePatternStandard()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    var loading by mutableStateOf(false)

    fun refresh() {
        viewModelScope.launch {

        }
    }

    fun ductTape() {
        _waybill.value = Resource.Initial
    }

    val acceptances: Flow<PagingData<PagingDataModel<Waybill>>> =
        getWaybillListPagingUseCase.invoke()
            .cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { PagingDataModel.Item(it) }
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
    fun createWaybill() {
        viewModelScope.launch {
            createWaybillUseCase(withoutParams())
                .collect {
                    loading = it is Resource.Loading
                    _waybill.value = it
                }
        }
    }

}

sealed class PagingDataModel<out T> {
    data class Item<out T>(val item: T) : PagingDataModel<T>()
    data class Separator(val text: String) : PagingDataModel<Nothing>()
}