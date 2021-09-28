package com.grappim.network.mappers.waybill

import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.network.model.waybill.WaybillProductDTO
import javax.inject.Inject

class WaybillProductMapper @Inject constructor(

) {

    fun dtoToDomain(from: WaybillProductDTO): WaybillProduct =
        WaybillProduct(
            amount = from.amount,
            barcode = from.barcode,
            createdOn = from.createdOn,
            id = from.id,
            name = from.name,
            productId = from.productId,
            purchasePrice = from.purchasePrice,
            sellingPrice = from.sellingPrice,
            updatedOn = from.updatedOn,
            waybillId = from.waybillId
        )
}