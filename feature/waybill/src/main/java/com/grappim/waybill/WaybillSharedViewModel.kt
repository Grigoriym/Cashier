package com.grappim.waybill

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.date_time.DateTimeStandard
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.Navigator
import com.grappim.waybill.details.WaybillDetailsFragmentDirections
import com.grappim.waybill.product.WaybillProductFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import java.math.BigDecimal
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WaybillSharedViewModel @Inject constructor(
    private val navigator: Navigator,
    private val waybillLocalRepository: WaybillLocalRepository
) : ViewModel() {

    fun getWaybillInWork(): Waybill = waybillLocalRepository.waybill

    val waybillFlow: StateFlow<Waybill>
        get() = waybillLocalRepository.waybillFlow
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = waybillLocalRepository.waybill
            )


    fun onBackPressed() {
        navigator.popBackStack()
    }

    fun showDetails(waybill: Waybill) {
        waybillLocalRepository.setWaybill(waybill)
        navigator.navigate(R.id.action_waybill_to_waybillDetails)
    }

    fun showWaybillProduct(waybillProduct: WaybillProduct) {
        navigator.navigate(
            R.id.action_waybill_to_product,
            bundleOf(
                WaybillProductFragment.ARG_WAYBILL_ID to getWaybillInWork().id,
                WaybillProductFragment.ARG_WAYBILL_PRODUCT to waybillProduct
            )
        )
    }

    fun showSearchProducts() {
        navigator.navigate(
            WaybillDetailsFragmentDirections.actionWaybillToSearch(
                waybillLocalRepository.waybill.id
            )
        )
    }

    fun setTotalCost(totalCost: BigDecimal) {
//        _waybill.value = _waybill.value!!.copy(totalCost = totalCost)
    }

    override fun onCleared() {
        waybillLocalRepository.clear()
        super.onCleared()
    }
}