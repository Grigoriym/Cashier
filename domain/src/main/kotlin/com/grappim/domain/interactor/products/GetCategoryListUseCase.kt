package com.grappim.domain.interactor.products

import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface GetCategoryListUseCase {
    fun getSimpleCategoryList(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>>
    fun categoriesInEditProduct(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>>
}