package com.grappim.domain.interactor.products

import com.grappim.domain.repository.GeneralRepository
import com.grappim.product_category.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryListUseCaseImpl @Inject constructor(
    private val generalRepository: GeneralRepository
) : GetCategoryListUseCase {

    override fun getSimpleCategoryList(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> =
        generalRepository.getCategories2(params)

    override fun categoriesInEditProduct(
        params: GetCategoryListInteractorParams
    ): Flow<List<ProductCategory>> =
        generalRepository.getCategoriesInEditProducts(params)
}