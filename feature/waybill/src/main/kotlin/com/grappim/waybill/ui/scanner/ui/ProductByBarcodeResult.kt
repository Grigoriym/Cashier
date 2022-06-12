package com.grappim.waybill.ui.scanner.ui

import com.grappim.domain.model.product.Product

sealed interface ProductByBarcodeResult {
    data class SuccessResult(
        val result: Product
    ) : ProductByBarcodeResult

    data class ErrorResult(
        val result: Throwable
    ) : ProductByBarcodeResult
}