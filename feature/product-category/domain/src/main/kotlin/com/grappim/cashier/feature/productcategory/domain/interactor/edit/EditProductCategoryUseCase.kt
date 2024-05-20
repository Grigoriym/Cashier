package com.grappim.cashier.feature.productcategory.domain.interactor.edit

import com.grappim.cashier.common.lce.VoidTry
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class EditProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    suspend fun execute(params: EditProductCategoryParams): VoidTry<Throwable> =
        productCategoryRepository.editProductCategory(params)
}
