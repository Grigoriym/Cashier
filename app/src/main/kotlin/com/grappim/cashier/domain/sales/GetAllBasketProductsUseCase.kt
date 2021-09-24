package com.grappim.cashier.domain.sales

import com.grappim.cashier.data.db.entity.BasketProductEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.repository.GeneralRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllBasketProductsUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

     operator fun invoke(): Flow<List<BasketProductEntity>> = generalRepository.getAllBasketProducts()

}