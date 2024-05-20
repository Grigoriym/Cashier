package com.grappim.feature.products.domain.interactor.editProduct

import com.grappim.cashier.common.lce.Try
import com.grappim.feature.products.domain.repository.ProductsRepository
import javax.inject.Inject

class EditProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun execute(params: EditProductParams): Try<Unit, Throwable> =
        productsRepository.updateProduct(params)
}
