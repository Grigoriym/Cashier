package com.grappim.product_category.domain.interactor

import com.grappim.common.lce.VoidTry

interface CreateProductCategoryUseCase {
    suspend fun execute(
        params: CreateProductCategoryParams
    ): VoidTry<Throwable>
}