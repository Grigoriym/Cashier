package com.grappim.domain.interactor.basket

import com.grappim.domain.base.CoroutineUseCase
import com.grappim.domain.base.NoParams
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteBagProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoroutineUseCase<NoParams, NoParams>(ioDispatcher) {

    override suspend fun execute(parameters: NoParams): NoParams =
        productsRepository.deleteBasketProducts()

}