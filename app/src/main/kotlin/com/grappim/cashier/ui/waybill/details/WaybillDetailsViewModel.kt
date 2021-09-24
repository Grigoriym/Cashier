package com.grappim.cashier.ui.waybill.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.di.modules.DateTimeStandard
import com.grappim.cashier.domain.waybill.*
import com.grappim.cashier.ui.waybill.WaybillStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WaybillDetailsViewModel @Inject constructor(
    private val waybillProductsUseCase: GetWaybillProductsUseCase,
    private val conductWaybillUseCase: ConductWaybillUseCase,
    private val rollbackWaybillUseCase: RollbackWaybillUseCase,
    @DateTimeStandard private val dateTimeStandard: DateTimeFormatter
) : ViewModel() {

    private val _waybillId = MutableLiveData<Int>()

    private val _waybillUpdate = mutableStateOf<Resource<Waybill>>(
        Resource.Initial
    )
    val waybillUpdate: State<Resource<Waybill>>
        get() = _waybillUpdate

    val products: Flow<PagingData<WaybillProduct>> =
        _waybillId.asFlow()
            .distinctUntilChanged()
            .flatMapLatest {
                waybillProductsUseCase.getProducts(it)
            }

    fun setWaybillId(waybillId: Int) {
        _waybillId.value = waybillId
    }

    fun updateWaybill(waybill: Waybill) {
        _waybillUpdate.value = Resource.Loading
        when (waybill.status) {
            WaybillStatus.DRAFT -> {
                viewModelScope.launch {
                    conductWaybillUseCase.invoke(waybill)
                        .onSuccess {
                            _waybillUpdate.value = Resource.Success(it)
                        }.onFailure {
                            _waybillUpdate.value = Resource.Error(it)
                        }
                }
            }
            WaybillStatus.ACTIVE -> {
                viewModelScope.launch {
                    rollbackWaybillUseCase.invoke(waybill)
                        .onSuccess {
                            _waybillUpdate.value = Resource.Success(it)
                        }.onFailure {
                            _waybillUpdate.value = Resource.Error(it)
                        }
                }
            }
        }
    }

    override fun onCleared() {
        Timber.d("cashier asd ${this::class.java.simpleName} onCleared")
        super.onCleared()
    }
}