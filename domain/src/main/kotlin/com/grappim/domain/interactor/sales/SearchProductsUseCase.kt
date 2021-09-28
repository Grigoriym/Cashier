package com.grappim.domain.interactor.sales

import com.grappim.domain.base.SimpleFlowUseCase
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SimpleFlowUseCase<SearchProductsUseCase.Params, List<Product>>(ioDispatcher) {

    data class Params(
        val query: String
    )

    override fun execute(params: Params): Flow<List<Product>> =
        productsRepository.searchProducts(params)
}