package com.grappim.cashier.domain.products

import com.grappim.cashier.domain.repository.GeneralRepository
import javax.inject.Inject

class DeleteBagProductsUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke() =
        generalRepository.deleteBagProducts()
}