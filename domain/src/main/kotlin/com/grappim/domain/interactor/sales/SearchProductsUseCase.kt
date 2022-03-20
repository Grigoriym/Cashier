package com.grappim.domain.interactor.sales

import androidx.paging.PagingData
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
) {

    data class Params(
        val query: String
    )

    fun execute(params: Params): Flow<PagingData<Product>> =
        productsRepository.searchProducts(params)
}