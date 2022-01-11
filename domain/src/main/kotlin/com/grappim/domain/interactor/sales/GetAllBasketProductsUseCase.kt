package com.grappim.domain.interactor.sales

import com.grappim.common.asynchronous.SimpleFlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBasketProductsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productsRepository: ProductsRepository
) : SimpleFlowUseCase<NoParams, List<BasketProduct>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<List<BasketProduct>> =
        productsRepository.getAllBasketProducts()

}