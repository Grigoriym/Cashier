package com.grappim.product_category.domain.interactor

import com.grappim.common.lce.VoidTry
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class EditProductCategoryUseCaseImpl @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) : EditProductCategoryUseCase {

    override suspend fun execute(
        params: EditProductCategoryParams
    ): VoidTry<Throwable> =
        productCategoryRepository.editProductCategory(params)

}