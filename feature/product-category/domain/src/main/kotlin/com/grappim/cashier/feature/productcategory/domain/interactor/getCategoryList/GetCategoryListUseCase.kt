package com.grappim.cashier.feature.productcategory.domain.interactor.getCategoryList

import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    fun getSimpleCategoryList(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> = productCategoryRepository.getCategories2(params)

    fun categoriesInEditProduct(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> = productCategoryRepository.getCategoriesInEditProducts(params)
}
