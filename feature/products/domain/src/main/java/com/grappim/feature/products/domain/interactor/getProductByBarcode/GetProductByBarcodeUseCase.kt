package com.grappim.feature.products.domain.interactor.getProductByBarcode

import com.grappim.cashier.common.lce.Try
import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductByBarcodeUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun execute(params: GetProductBarcodeParams): Try<Product, Throwable> =
        productsRepository.getProductByBarcode(params)
}
