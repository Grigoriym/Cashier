package com.grappim.product_category.domain.interactor

import com.grappim.common.lce.VoidTry

interface EditProductCategoryUseCase {
    suspend fun execute(
        params: EditProductCategoryParams
    ): VoidTry<Throwable>
}