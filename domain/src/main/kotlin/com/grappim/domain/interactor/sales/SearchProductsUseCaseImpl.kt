package com.grappim.domain.interactor.sales

import androidx.paging.PagingData
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
) : SearchProductsUseCase {

    override fun execute(params: SearchProductsParams): Flow<PagingData<Product>> =
        productsRepository.searchProducts(params)
}