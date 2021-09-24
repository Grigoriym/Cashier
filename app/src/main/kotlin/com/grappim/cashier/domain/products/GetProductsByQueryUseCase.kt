package com.grappim.cashier.domain.products

import com.grappim.cashier.data.db.entity.CategoryEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByQueryUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {
    operator fun invoke(
        categoryEntity: CategoryEntity?,
        query: String
    ): Flow<List<ProductEntity>> = generalRepository.getProductsByQuery(categoryEntity, query)
}