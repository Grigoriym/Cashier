package com.grappim.waybill.ui.scanner.ui

import com.grappim.domain.model.waybill.WaybillProduct

sealed interface WaybillProductByBarcodeResult {
    data class SuccessResult(
        val result: WaybillProduct
    ) : WaybillProductByBarcodeResult
}