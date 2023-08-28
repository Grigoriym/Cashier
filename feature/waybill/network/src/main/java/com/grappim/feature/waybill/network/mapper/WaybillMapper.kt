package com.grappim.feature.waybill.network.mapper

import com.grappim.datetime.DateTimeIsoLocalDateTime
import com.grappim.datetime.DateTimeStandard
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.network.model.WaybillDTO
import com.grappim.logger.logD
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WaybillMapper @Inject constructor(
    @DateTimeStandard private val dtfStandard: DateTimeFormatter,
    @DateTimeIsoLocalDateTime private val dtfIso: DateTimeFormatter
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
            status = from.status,
            stockId = from.stockId,
            totalCost = from.totalCost,
//            type = WaybillType.getTypeByValue(from.type),
            updatedOn = from.updatedOn,
            reservedTime = from.reservedTime,
            comment = from.comment,
            updateOnToDemonstrate = dtfStandard.format(
                dtfIso.parse(from.updatedOn)
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
            status = from.status,
            stockId = from.stockId,
            totalCost = from.totalCost,
//            type = from.type.value,
            updatedOn = from.updatedOn,
            reservedTime = from.reservedTime,
            comment = from.comment
        )
}
