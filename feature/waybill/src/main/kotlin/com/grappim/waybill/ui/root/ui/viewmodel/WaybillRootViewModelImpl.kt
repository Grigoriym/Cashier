package com.grappim.waybill.ui.root.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.math.BigDecimal
import javax.inject.Inject

class WaybillRootViewModelImpl @Inject constructor(
    private val waybillLocalRepository: WaybillLocalRepository,
    private val waybillScreenNavigator: WaybillScreenNavigator
) : WaybillRootViewModel() {

    fun getWaybillInWork(): Waybill = waybillLocalRepository.waybill

    override val waybillFlow: StateFlow<Waybill>
        get() = waybillLocalRepository.waybillFlow
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = waybillLocalRepository.waybill
            )


    override fun onBackPressed() {
        waybillScreenNavigator.goBack()
    }

    fun showDetails(waybill: Waybill) {
        waybillLocalRepository.setWaybill(waybill)
//        navigator.navigate(R.id.action_waybill_to_waybillDetails)
    }

    override fun showWaybillProduct(waybillProduct: WaybillProduct) {
        waybillScreenNavigator.goToWaybillProduct()
//        navigator.navigate(
//            R.id.action_waybill_to_product,
//            bundleOf(
//                WaybillProductFragment.ARG_WAYBILL_ID to getWaybillInWork().id,
//                WaybillProductFragment.ARG_WAYBILL_PRODUCT to waybillProduct
//            )
//        )
    }

    override fun showSearchProducts() {
        waybillScreenNavigator.goToProductSearch()
//        navigator.navigate(
//            WaybillDetailsFragmentDirections.actionWaybillToSearch(
//                waybillLocalRepository.waybill.id
//            )
//        )
    }

    fun setTotalCost(totalCost: BigDecimal) {
//        _waybill.value = _waybill.value!!.copy(totalCost = totalCost)
    }

    override fun onCleared() {
        waybillLocalRepository.clear()
        super.onCleared()
    }
}