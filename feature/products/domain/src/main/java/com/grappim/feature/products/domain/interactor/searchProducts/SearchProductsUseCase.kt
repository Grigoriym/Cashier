package com.grappim.feature.products.domain.interactor.searchProducts

import androidx.paging.PagingData
import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
) {

    fun execute(params: SearchProductsParams): Flow<PagingData<Product>> =
        productsRepository.searchProducts(params)
}