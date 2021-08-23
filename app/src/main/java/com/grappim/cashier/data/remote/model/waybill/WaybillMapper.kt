package com.grappim.cashier.data.remote.model.waybill

import com.grappim.cashier.core.extensions.getOffsetDateTimeFromString
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.domain.waybill.WaybillProduct
import com.grappim.cashier.ui.waybill.WaybillStatus
import com.grappim.cashier.ui.waybill.WaybillType

object WaybillMapper {

    private val dtfDateTime = DateTimeUtils.getDateTimePatternStandard()

    fun WaybillDTO.toDomain(): Waybill =
        Waybill(
            createdOn = createdOn,
            id = id,
            merchantId = merchantId,
            number = number,
            status = WaybillStatus.getStatusByValue(status),
            stockId = stockId,
            totalCost = totalCost,
            type = WaybillType.getTypeByValue(type),
            updatedOn = updatedOn,
            reservedTime = reservedTime,
            comment = comment,
            updateOnToDemonstrate = dtfDateTime.format(
                updatedOn.getOffsetDateTimeFromString()
            )
        )

    fun Waybill.toDTO(): WaybillDTO =
        WaybillDTO(
            createdOn = createdOn,
            id = id,
            merchantId = merchantId,
            number = number,
            status = status.value,
            stockId = stockId,
            totalCost = totalCost,
            type = type.value,
            updatedOn = updatedOn,
            reservedTime = reservedTime,
            comment = comment
        )

    fun WaybillProductDTO.toDomain(): WaybillProduct =
        WaybillProduct(
            amount = amount,
            barcode = barcode,
            createdOn = createdOn,
            id = id,
            name = name,
            productId = productId,
            purchasePrice = purchasePrice,
            sellingPrice = sellingPrice,
            updatedOn = updatedOn,
            waybillId = waybillId
        )
}