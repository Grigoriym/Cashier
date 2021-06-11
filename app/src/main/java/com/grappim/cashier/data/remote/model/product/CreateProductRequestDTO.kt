package com.grappim.cashier.data.remote.model.product

import com.grappim.cashier.domain.products.CreateProductUseCase

data class CreateProductRequestDTO(
    val product: CreateProductUseCase.CreateProductParams
)