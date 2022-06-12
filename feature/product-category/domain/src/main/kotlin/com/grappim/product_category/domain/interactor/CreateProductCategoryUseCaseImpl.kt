package com.grappim.product_category.domain.interactor

import com.grappim.common.lce.VoidTry
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class CreateProductCategoryUseCaseImpl @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) : CreateProductCategoryUseCase {

    override suspend fun execute(
        params: CreateProductCategoryParams
    ): VoidTry<Throwable> =
        productCategoryRepository.createProductCategory(params)

}