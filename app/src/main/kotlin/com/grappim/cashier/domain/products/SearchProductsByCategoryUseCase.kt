package com.grappim.cashier.domain.products

import com.grappim.cashier.data.db.entity.CategoryEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import javax.inject.Inject

class SearchProductsByCategoryUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke(categoryEntity: CategoryEntity): List<ProductEntity> =
        generalRepository.getProductsByCategory(categoryEntity)
}