package com.grappim.domain.interactor.waybill

data class GetWaybillProductByBarcodeParams(
    val barcode: String,
    val waybillId: Long
)
