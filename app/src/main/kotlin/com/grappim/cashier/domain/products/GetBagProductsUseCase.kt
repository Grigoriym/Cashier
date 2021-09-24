package com.grappim.cashier.domain.products

import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import javax.inject.Inject

class GetBagProductsUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke(): List<ProductEntity> =
        generalRepository.getBagProducts()
}