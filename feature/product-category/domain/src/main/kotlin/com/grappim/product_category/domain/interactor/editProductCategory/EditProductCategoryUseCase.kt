package com.grappim.product_category.domain.interactor.editProductCategory

import com.grappim.common.lce.VoidTry
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class EditProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    suspend fun execute(
        params: EditProductCategoryParams
    ): VoidTry<Throwable> =
        productCategoryRepository.editProductCategory(params)

}
