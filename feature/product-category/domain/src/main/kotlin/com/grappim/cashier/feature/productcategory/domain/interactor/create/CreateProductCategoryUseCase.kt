package com.grappim.cashier.feature.productcategory.domain.interactor.create

import com.grappim.cashier.common.lce.VoidTry
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import javax.inject.Inject

class CreateProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository
) {

    suspend fun execute(params: CreateProductCategoryParams): VoidTry<Throwable> =
        productCategoryRepository.createProductCategory(params)
}
