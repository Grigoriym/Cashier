package com.grappim.feature.waybill.repository.local

import com.grappim.common.di.AppScope
import com.grappim.date_time.DateTimeIsoLocalDateTime
import com.grappim.date_time.DateTimeStandard
import com.grappim.date_time.getZonedDateTimeWithFormatter
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.logger.logD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AppScope
class WaybillLocalRepositoryImpl @Inject constructor(
    @DateTimeIsoLocalDateTime private val dtfIso: DateTimeFormatter,
    @DateTimeStandard private val dtfStandard: DateTimeFormatter
) : WaybillLocalRepository {

    private var _waybill: Waybill? = null

    private val _waybillFlow = MutableStateFlow<Waybill?>(null)

    override val waybillFlow: Flow<Waybill>
        get() = _waybillFlow.asStateFlow()
            .filterNotNull()

    override val waybill: Waybill
        get() = requireNotNull(_waybill)

    override fun setWaybill(waybill: Waybill) {
        _waybill = waybill
        _waybillFlow.value = waybill
    }

    override fun setComment(text: String) {
        _waybill = _waybill?.copy(comment = text)
        _waybillFlow.value = _waybill
    }

    override fun setActualDate(text: String) {
        logD("actualDate: $text")//10.11.2021 12:18

        val parsedDate = text.getZonedDateTimeWithFormatter(false, dtfStandard)
        logD("parsedDate: $parsedDate")
        val isoDate = dtfIso.format(parsedDate)
        logD("isoDate: $isoDate")

        _waybill = _waybill?.copy(
            reservedTime = isoDate,
            reservedTimeToDemonstrate = text
        )
        _waybillFlow.value = _waybill
    }

    override fun clear() {
        _waybill = null
        _waybillFlow.value = null
    }

}