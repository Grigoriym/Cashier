package com.grappim.waybill.ui.details.ui.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.domain.interactor.waybill.*
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

    override val products: Flow<PagingData<WaybillProduct>> =
        waybillLocalRepository.waybillFlow
            .flatMapLatest {
                waybillProductsUseCase
                    .execute(GetWaybillProductsParams(it.id))
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
                    _loading.value = true
                    val result = conductWaybillUseCase.execute(ConductWaybillParams(waybill))
                    _loading.value = false
                    when (result) {
                        is Try.Success -> {
                            waybillCreatedUpdated()
                        }
                        is Try.Error -> {
                            _error.value = result.result
                        }
                    }
                }
            }
            WaybillStatus.ACTIVE -> {
                viewModelScope.launch {
                    _loading.value = true
                    val result = rollbackWaybillUseCase.execute(RollbackWaybillParams(waybill))
                    _loading.value = false
                    when (result) {
                        is Try.Success -> {
                            waybillCreatedUpdated()
                        }
                        is Try.Error -> {
                            _error.value = result.result
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
        onBackPressed()
    }
}