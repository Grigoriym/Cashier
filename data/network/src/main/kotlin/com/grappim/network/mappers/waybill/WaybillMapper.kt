package com.grappim.network.mappers.waybill

import com.grappim.date_time.DateTimeIsoInstant
import com.grappim.date_time.DateTimeStandard
import com.grappim.date_time.getOffsetDateTimeFromString
import com.grappim.date_time.getZonedDateTimeWithFormatter
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.domain.model.waybill.WaybillType
import com.grappim.logger.logD
import com.grappim.network.model.waybill.WaybillDTO
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WaybillMapper @Inject constructor(
    @DateTimeStandard private val dtfStandard: DateTimeFormatter,
    @DateTimeIsoInstant private val dtfIso: DateTimeFormatter
) {

    fun dtoToDomain(from: WaybillDTO): Waybill {
        val reservedTimeToDemonstrate = if (from.reservedTime != null) {
            val parsed = dtfIso.parse(from.reservedTime)
            val zdt = Instant.from(parsed).atZone(ZoneId.systemDefault())
            val formatted = dtfStandard.format(zdt)
            logD("formatted: $formatted, orig: ${from.reservedTime}")
            formatted
        } else {
            null
        }
        return Waybill(
            createdOn = from.createdOn,
            id = from.id,
            merchantId = from.merchantId,
            number = from.number,
            status = WaybillStatus.getStatusByValue(from.status),
            stockId = from.stockId,
            totalCost = from.totalCost,
            type = WaybillType.getTypeByValue(from.type),
            updatedOn = from.updatedOn,
            reservedTime = from.reservedTime,
            comment = from.comment,
            updateOnToDemonstrate = dtfStandard.format(
                from.updatedOn.getOffsetDateTimeFromString()
            ),
            reservedTimeToDemonstrate = reservedTimeToDemonstrate
        )
    }

    fun domainToDto(from: Waybill): WaybillDTO =
        WaybillDTO(
            createdOn = from.createdOn,
            id = from.id,
            merchantId = from.merchantId,
            number = from.number,
            status = from.status.value,
            stockId = from.stockId,
            totalCost = from.totalCost,
            type = from.type.value,
            updatedOn = from.updatedOn,
            reservedTime = from.reservedTime,
            comment = from.comment
        )
}