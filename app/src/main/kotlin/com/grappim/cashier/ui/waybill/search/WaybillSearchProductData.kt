package com.grappim.cashier.ui.waybill.search

import com.grappim.db.entity.ProductEntity

data class WaybillSearchProductData(
    val product: ProductEntity,
    val waybillId: Int
)
