package com.grappim.domain.interactor.basket

import com.grappim.domain.model.basket.BasketProduct
import kotlinx.coroutines.flow.Flow

interface GetBasketItemsUseCase {

    fun execute(): Flow<List<BasketProduct>>

}