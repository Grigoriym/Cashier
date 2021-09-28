package com.grappim.cashier.ui.waybill

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.date_time.DateTimeStandard
import com.grappim.domain.model.waybill.Waybill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WaybillSharedViewModel @Inject constructor(
    @DateTimeStandard private val dateTimeStandard: DateTimeFormatter
) : ViewModel() {

    private val _waybill = mutableStateOf<Waybill?>(null)
    val waybill: State<Waybill?>
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
        val isoInstant = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(time))
        val zonedDateTime = ZonedDateTime.ofInstant(isoInstant, ZonedDateTime.now().zone)
        val toDemonstrate = dateTimeStandard.format(zonedDateTime)
        _waybill.value = _waybill.value!!.copy(
            reservedTime = time,
            reservedTimeToDemonstrate = toDemonstrate
        )
    }

    fun setTotalCost(totalCost: BigDecimal) {
        _waybill.value = _waybill.value!!.copy(totalCost = totalCost)
    }
}