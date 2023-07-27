package com.grappim.feature.waybill.presentation.ui.details.ui.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.core.utils.BundleArgsHelper
import com.grappim.feature.waybill.domain.interactor.conductWaybill.ConductWaybillParams
import com.grappim.feature.waybill.domain.interactor.conductWaybill.ConductWaybillUseCase
import com.grappim.feature.waybill.domain.interactor.getWaybillProducts.GetWaybillProductsParams
import com.grappim.feature.waybill.domain.interactor.getWaybillProducts.GetWaybillProductsUseCase
import com.grappim.feature.waybill.domain.interactor.rollbackWaybill.RollbackWaybillParams
import com.grappim.feature.waybill.domain.interactor.rollbackWaybill.RollbackWaybillUseCase
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.domain.model.WaybillStatus
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
