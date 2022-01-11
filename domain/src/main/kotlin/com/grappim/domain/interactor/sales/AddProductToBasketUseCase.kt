package com.grappim.domain.interactor.sales

import com.grappim.common.asynchronous.CoroutineUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AddProductToBasketUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<AddProductToBasketUseCase.Params, NoParams>(ioDispatcher) {


    data class Params(
        val product: Product
    )

    override suspend fun execute(parameters: Params): NoParams =
        productsRepository.addBasketProduct(parameters)
}