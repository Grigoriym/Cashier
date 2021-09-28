package com.grappim.domain.interactor.sales

import com.grappim.domain.base.NoParams
import com.grappim.domain.base.SimpleFlowUseCase
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.basket.Basket
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