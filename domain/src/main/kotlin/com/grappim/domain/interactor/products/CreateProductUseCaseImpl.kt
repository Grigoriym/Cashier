package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try
import com.grappim.domain.repository.ProductsRepository
import javax.inject.Inject

class CreateProductUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : CreateProductUseCase {

    override suspend fun execute(params: CreateProductParams): Try<Unit, Throwable> =
        productsRepository.createProduct(params)

}