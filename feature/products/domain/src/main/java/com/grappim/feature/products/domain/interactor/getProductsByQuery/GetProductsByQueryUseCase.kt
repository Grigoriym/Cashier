package com.grappim.feature.products.domain.interactor.getProductsByQuery

import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByQueryUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    fun execute(params: GetProductsByQueryParams): Flow<List<Product>> =
        productsRepository.getProductsByQuery(params)
}