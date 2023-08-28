package com.grappim.productcategory.domain.interactor.getProductCategories

import com.grappim.productcategory.domain.model.ProductCategory
import com.grappim.productcategory.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductCategoriesUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    fun execute(): Flow<List<ProductCategory>> =
        productCategoryRepository.categoriesFlow()

}
