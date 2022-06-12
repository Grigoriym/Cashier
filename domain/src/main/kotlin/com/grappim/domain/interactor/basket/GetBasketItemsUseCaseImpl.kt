package com.grappim.domain.interactor.basket

import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketItemsUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : GetBasketItemsUseCase {

    override fun execute(): Flow<List<BasketProduct>> =
        basketRepository.getBasketItems()

}