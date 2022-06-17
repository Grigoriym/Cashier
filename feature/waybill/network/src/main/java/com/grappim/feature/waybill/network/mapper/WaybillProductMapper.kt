package com.grappim.feature.waybill.network.mapper

import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.feature.waybill.network.model.WaybillProductDTO

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