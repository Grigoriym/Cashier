package com.grappim.product_category.domain.interactor.createProductCateogry

import com.grappim.common.lce.VoidTry
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class CreateProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    suspend fun execute(
        params: CreateProductCategoryParams
    ): VoidTry<Throwable> =
        productCategoryRepository.createProductCategory(params)

}