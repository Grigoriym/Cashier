package com.grappim.cashier.domain.sales

import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke(query: String): List<ProductEntity> =
        generalRepository.searchProducts(query)
}