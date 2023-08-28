package com.grappim.productcategory.domain.interactor.edit

import com.grappim.common.lce.VoidTry
import com.grappim.productcategory.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class EditProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    suspend fun execute(
        params: EditProductCategoryParams
    ): VoidTry<Throwable> =
        productCategoryRepository.editProductCategory(params)

}
