package com.grappim.feature.waybill.presentation.ui.scanner.ui

import com.grappim.feature.waybill.domain.model.WaybillProduct

sealed interface WaybillProductByBarcodeResult {
    data class SuccessResult(
        val result: WaybillProduct
    ) : WaybillProductByBarcodeResult
}
