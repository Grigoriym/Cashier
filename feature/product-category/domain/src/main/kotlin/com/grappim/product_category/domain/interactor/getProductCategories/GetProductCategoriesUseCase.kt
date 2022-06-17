package com.grappim.product_category.domain.interactor.getProductCategories

import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductCategoriesUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    fun execute(): Flow<List<ProductCategory>> =
        productCategoryRepository.categoriesFlow()

}