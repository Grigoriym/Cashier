package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try

interface EditProductUseCase {
    suspend fun execute(params: EditProductParams): Try<Unit, Throwable>
}