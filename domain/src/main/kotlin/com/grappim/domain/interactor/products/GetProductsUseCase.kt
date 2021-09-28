package com.grappim.domain.interactor.products

import com.grappim.domain.base.NoParams
import com.grappim.domain.base.SimpleFlowUseCase
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SimpleFlowUseCase<NoParams, List<Product>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<List<Product>> =
        productsRepository.getProducts()

}