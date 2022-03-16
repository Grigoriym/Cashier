package com.grappim.waybill.ui.details.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.domain.interactor.waybill.ConductWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductsUseCase
import com.grappim.domain.interactor.waybill.RollbackWaybillUseCase
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.domain.repository.local.WaybillLocalRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class WaybillDetailsViewModelImpl @Inject constructor(
    private val waybillProductsUseCase: GetWaybillProductsUseCase,
    private val conductWaybillUseCase: ConductWaybillUseCase,
    private val rollbackWaybillUseCase: RollbackWaybillUseCase,
    private val waybillLocalRepository: WaybillLocalRepository
) : WaybillDetailsViewModel() {

    override val comment: StateFlow<String>
        get() = waybillLocalRepository.waybillFlow
            .map {
                it.comment
            }.stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = ""
            )

    override val actualDate: StateFlow<String>
        get() = waybillLocalRepository.waybillFlow
            .map {
                it.reservedTimeToDemonstrate ?: ""
            }.stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = ""
            )

    override val waybillUpdate = mutableStateOf<Try<Waybill>>(Try.Initial)
    override val products: Flow<PagingData<WaybillProduct>> =
        waybillLocalRepository.waybillFlow
            .flatMapLatest {
                waybillProductsUseCase(GetWaybillProductsUseCase.Params(it.id))
            }

    override fun setComment(text: String) {
        waybillLocalRepository.setComment(text)
    }

    override fun setActualDate(date: String) {
        waybillLocalRepository.setActualDate(date)
    }

    override fun updateWaybill(waybill: Waybill) {
        when (waybill.status) {
            WaybillStatus.DRAFT -> {
                viewModelScope.launch {
                    conductWaybillUseCase.invoke(ConductWaybillUseCase.Params(waybill))
                        .collect {
                            _loading.value = it is Try.Loading
                            when (it) {
                                is Try.Success -> {
                                    waybillCreatedUpdated()
                                }
                                is Try.Error -> {
                                    _error.value = it.exception
                                }
                            }
                        }
                }
            }
            WaybillStatus.ACTIVE -> {
                viewModelScope.launch {
                    rollbackWaybillUseCase.invoke(RollbackWaybillUseCase.Params(waybill))
                        .collect {
                            _loading.value = it is Try.Loading
                            when (it) {
                                is Try.Success -> {
                                    waybillCreatedUpdated()
                                }
                                is Try.Error -> {
                                    _error.value = it.exception
                                }
                            }
                        }
                }
            }
        }
    }

    override fun showWaybillProduct(waybillProduct: WaybillProduct) {
        val args = bundleOf(
            BundleArgsHelper.Waybill.ARG_KEY_WAYBILL_PRODUCT to waybillProduct
        )
        flowRouter.goToWaybillProduct(args)
    }

    override fun showSearchProducts() {
        flowRouter.goToWaybillSearch()
    }

    override fun showScanner() {
        flowRouter.goToWaybillScanner()
    }

    private fun waybillCreatedUpdated() {
        onBackPressed3()
    }
}