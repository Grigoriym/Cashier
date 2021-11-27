package com.grappim.domain.interactor.products

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.NoParams
import com.grappim.domain.base.Try
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBagProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<NoParams, List<Product>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<Try<List<Product>>> =
        productsRepository.getBagProducts()

}