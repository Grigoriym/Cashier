package com.grappim.domain.interactor.sales

import com.grappim.domain.base.CoroutineUseCase
import com.grappim.domain.base.NoParams
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoveProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<RemoveProductUseCase.Params, NoParams>(ioDispatcher) {

    data class Params(
        val product: Product
    )

    override suspend fun execute(parameters: Params): NoParams =
        productsRepository.removeBasketProduct(parameters)
}