package com.grappim.cashier.ui.waybill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.domain.waybill.Waybill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class WaybillSharedViewModel @Inject constructor(

) : ViewModel() {

    private val _waybill = MutableLiveData<Waybill>()
    val waybill: LiveData<Waybill>
        get() = _waybill

    fun setCurrentWaybill(waybill: Waybill) {
        viewModelScope.launch {
            _waybill.value = waybill
        }
    }

    fun setComment(comment: String) {
        _waybill.value = _waybill.value!!.copy(comment = comment)
    }

    fun setReservedTime(time: String) {
        _waybill.value = _waybill.value!!.copy(reservedTime = time)
    }

    fun setTotalCost(totalCost: BigDecimal) {
        _waybill.value = _waybill.value!!.copy(totalCost = totalCost)
    }
}