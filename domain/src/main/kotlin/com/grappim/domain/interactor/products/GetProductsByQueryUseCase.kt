package com.grappim.domain.interactor.products

import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface GetProductsByQueryUseCase {
    fun execute(
        params: GetProductsByQueryParams
    ): Flow<List<Product>>
}