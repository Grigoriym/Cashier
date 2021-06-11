package com.grappim.cashier.ui.waybill.list

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.grappim.cashier.core.extensions.getOffsetDateTimeFromString
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.core.platform.SingleLiveEvent
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.domain.waybill.CreateWaybillUseCase
import com.grappim.cashier.domain.waybill.GetWaybillListPagingUseCase
import com.grappim.cashier.domain.waybill.Waybill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaybillListViewModel @Inject constructor(
    getWaybillListPagingUseCase: GetWaybillListPagingUseCase,
    private val createWaybillUseCase: CreateWaybillUseCase
) : ViewModel() {

    private val _waybill = SingleLiveEvent<Resource<Waybill>>()
    val waybill: LiveData<Resource<Waybill>>
        get() = _waybill

    private val df = DateTimeUtils.getDatePatternStandard()

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
            _waybill.value = Resource.Loading
            createWaybillUseCase.invoke()
                .onFailure {
                    _waybill.value = Resource.Error(it)
                }.onSuccess {
                    _waybill.value = Resource.Success(it)
                }
        }
    }

}

sealed class PagingDataModel<out T> {
    data class Item<out T>(val item: T) : PagingDataModel<T>()
    data class Separator(val text: String) : PagingDataModel<Nothing>()
}