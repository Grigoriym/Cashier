package com.grappim.waybill.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.base.Try
import com.grappim.domain.interactor.waybill.ConductWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductsUseCase
import com.grappim.domain.interactor.waybill.RollbackWaybillUseCase
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.logger.logD
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaybillDetailsViewModel @Inject constructor(
    private val waybillProductsUseCase: GetWaybillProductsUseCase,
    private val conductWaybillUseCase: ConductWaybillUseCase,
    private val rollbackWaybillUseCase: RollbackWaybillUseCase,
    private val waybillLocalRepository: WaybillLocalRepository,
    private val navigator: Navigator
) : ViewModel() {

    val comment: StateFlow<String>
        get() = waybillLocalRepository.waybillFlow
            .map {
                it.comment
            }.stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = ""
            )

    val actualDate: StateFlow<String>
        get() = waybillLocalRepository.waybillFlow
            .map {
                it.reservedTimeToDemonstrate ?: ""
            }.stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = ""
            )

    fun setComment(text: String) {
        waybillLocalRepository.setComment(text)
    }

    fun setActualDate(date: String) {
        waybillLocalRepository.setActualDate(date)
    }

    private val _waybillUpdate = mutableStateOf<Try<Waybill>>(
        Try.Initial
    )
    val waybillUpdate: State<Try<Waybill>>
        get() = _waybillUpdate

    val products: Flow<PagingData<WaybillProduct>> =
        waybillLocalRepository.waybillFlow
            .flatMapLatest {
                waybillProductsUseCase(GetWaybillProductsUseCase.Params(it.id))
            }

    fun updateWaybill(waybill: Waybill) {
        _waybillUpdate.value = Try.Loading
        when (waybill.status) {
            WaybillStatus.DRAFT -> {
                viewModelScope.launch {
                    conductWaybillUseCase.invoke(ConductWaybillUseCase.Params(waybill))
                        .collect {
                            _waybillUpdate.value = it
                            when (it) {
                                is Try.Success -> {
                                    waybillCreatedUpdated()
                                }
                            }
                        }
                }
            }
            WaybillStatus.ACTIVE -> {
                viewModelScope.launch {
                    rollbackWaybillUseCase.invoke(RollbackWaybillUseCase.Params(waybill))
                        .collect {
                            _waybillUpdate.value = it
                            when (it) {
                                is Try.Success -> {
                                    waybillCreatedUpdated()
                                }
                            }
                        }
                }
            }
        }
    }

    fun showScanner() {
        navigator.navigate(
            WaybillDetailsFragmentDirections.actionWaybillDetailsToWaybillScanner(
                waybillId = waybillLocalRepository.waybill.id
            )
        )
    }

    private fun waybillCreatedUpdated() {
        navigator.navigate(WaybillDetailsFragmentDirections.actionWaybillDetailsToWaybillList())
    }

    override fun onCleared() {
        logD("cashier asd ${this::class.java.simpleName} onCleared")
        super.onCleared()
    }
}