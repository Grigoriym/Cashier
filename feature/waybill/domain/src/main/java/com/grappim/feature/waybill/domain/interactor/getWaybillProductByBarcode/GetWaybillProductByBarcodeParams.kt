package com.grappim.feature.waybill.domain.interactor.getWaybillProductByBarcode

data class GetWaybillProductByBarcodeParams(
    val barcode: String,
    val waybillId: Long
)
