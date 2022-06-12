package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try
import com.grappim.domain.repository.ProductsRepository
import javax.inject.Inject

class EditProductUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository
) : EditProductUseCase {

    override suspend fun execute(params: EditProductParams): Try<Unit, Throwable> =
        productsRepository.updateProduct(params)

}

