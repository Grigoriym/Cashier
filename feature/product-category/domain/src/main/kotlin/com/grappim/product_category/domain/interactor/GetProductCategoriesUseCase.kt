package com.grappim.product_category.domain.interactor

import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface GetProductCategoriesUseCase {
    fun execute(): Flow<List<ProductCategory>>
}