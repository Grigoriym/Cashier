package com.grappim.waybill.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.grappim.domain.base.Result
import com.grappim.domain.interactor.waybill.ConductWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductsUseCase
import com.grappim.domain.interactor.waybill.RollbackWaybillUseCase
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.logger.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaybillDetailsViewModel @Inject constructor(
    private val waybillProductsUseCase: GetWaybillProductsUseCase,
    private val conductWaybillUseCase: ConductWaybillUseCase,
    private val rollbackWaybillUseCase: RollbackWaybillUseCase
) : ViewModel() {

    private val _waybillId = MutableLiveData<Int>()

    private val _waybillUpdate = mutableStateOf<Result<Waybill>>(
        Result.Initial
    )
    val waybillUpdate: State<Result<Waybill>>
        get() = _waybillUpdate

    val products: Flow<PagingData<WaybillProduct>> =
        _waybillId.asFlow()
            .distinctUntilChanged()
            .flatMapLatest {
                waybillProductsUseCase(GetWaybillProductsUseCase.Params(it))
            }

    fun setWaybillId(waybillId: Int) {
        _waybillId.value = waybillId
    }

    fun updateWaybill(waybill: Waybill) {
        _waybillUpdate.value = Result.Loading
        when (waybill.status) {
            WaybillStatus.DRAFT -> {
                viewModelScope.launch {
                    conductWaybillUseCase.invoke(ConductWaybillUseCase.Params(waybill))
                        .collect {
                            _waybillUpdate.value = it
                        }
                }
            }
            WaybillStatus.ACTIVE -> {
                viewModelScope.launch {
                    rollbackWaybillUseCase.invoke(RollbackWaybillUseCase.Params(waybill))
                        .collect {
                            _waybillUpdate.value = it
                        }
                }
            }
        }
    }

    override fun onCleared() {
        logD("cashier asd ${this::class.java.simpleName} onCleared")
        super.onCleared()
    }
}