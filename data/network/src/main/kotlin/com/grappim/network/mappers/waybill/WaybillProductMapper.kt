package com.grappim.network.mappers.waybill

import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.network.model.waybill.WaybillProductDTO

fun WaybillProductDTO.toDomain(): WaybillProduct =
    WaybillProduct(
        amount = this.amount,
        barcode = this.barcode,
        createdOn = this.createdOn,
        id = this.id,
        name = this.name,
        productId = this.productId,
        purchasePrice = this.purchasePrice,
        sellingPrice = this.sellingPrice,
        updatedOn = this.updatedOn,
        waybillId = this.waybillId
    )