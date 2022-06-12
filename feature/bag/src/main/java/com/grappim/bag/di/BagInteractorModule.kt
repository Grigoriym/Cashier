package com.grappim.bag.di

import com.grappim.domain.interactor.basket.ClearBasketUseCase
import com.grappim.domain.interactor.basket.ClearBasketUseCaseImpl
import com.grappim.domain.interactor.products.GetBagProductsUseCase
import com.grappim.domain.interactor.products.GetBagProductsUseCaseImpl
import com.grappim.domain.interactor.sales.AddBasketProductUseCase
import com.grappim.domain.interactor.sales.AddBasketProductUseCaseImpl
import com.grappim.domain.interactor.sales.RemoveProductUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface BagInteractorModule {

    @Binds
    fun bindClearBasketUseCase(
        clearBasketUseCaseImpl: ClearBasketUseCaseImpl
    ): ClearBasketUseCase

    @Binds
    fun bindAddBasketProduct(
        addBasketProductUseCaseImpl: AddBasketProductUseCaseImpl
    ): AddBasketProductUseCase

    @Binds
    fun bindGetBagProductsUseCase(
        getBagProductsUseCaseImpl: GetBagProductsUseCaseImpl
    ): GetBagProductsUseCase

    @Binds
    fun bindRemoveProductUseCase(
        removeProductUseCaseImpl: RemoveProductUseCaseImpl
    ): RemoveProductUseCase
}