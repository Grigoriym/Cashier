package com.grappim.cashier.domain.products

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke(): Either<Throwable, List<ProductEntity>> =
        generalRepository.getProducts()
}