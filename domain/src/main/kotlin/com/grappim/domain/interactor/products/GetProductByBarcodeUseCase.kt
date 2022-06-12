package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try
import com.grappim.domain.model.product.Product

interface GetProductByBarcodeUseCase {
    suspend fun execute(params: GetProductBarcodeParams): Try<Product, Throwable>
}