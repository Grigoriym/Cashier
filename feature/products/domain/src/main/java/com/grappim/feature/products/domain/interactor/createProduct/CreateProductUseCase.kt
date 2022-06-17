package com.grappim.feature.products.domain.interactor.createProduct

import com.grappim.common.lce.Try
import com.grappim.feature.products.domain.repository.ProductsRepository
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun execute(params: CreateProductParams): Try<Unit, Throwable> =
        productsRepository.createProduct(params)

}