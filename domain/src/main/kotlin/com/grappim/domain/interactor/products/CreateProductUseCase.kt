package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try

interface CreateProductUseCase {
    suspend fun execute(params: CreateProductParams): Try<Unit, Throwable>
}