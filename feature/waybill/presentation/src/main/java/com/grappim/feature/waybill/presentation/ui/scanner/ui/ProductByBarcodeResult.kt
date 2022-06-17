package com.grappim.feature.waybill.presentation.ui.scanner.ui

import com.grappim.domain.model.Product

sealed interface ProductByBarcodeResult {
    data class SuccessResult(
        val result: Product
    ) : ProductByBarcodeResult

    data class ErrorResult(
        val result: Throwable
    ) : ProductByBarcodeResult
}