package com.grappim.product_category.domain.interactor

import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductCategoriesUseCaseImpl @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) : GetProductCategoriesUseCase {

    override fun execute(): Flow<List<ProductCategory>> =
        productCategoryRepository.categoriesFlow()

}