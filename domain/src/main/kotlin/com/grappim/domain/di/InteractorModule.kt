package com.grappim.domain.di

import com.grappim.domain.interactor.basket.GetBasketItemsUseCase
import com.grappim.domain.interactor.basket.GetBasketItemsUseCaseImpl
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetCategoryListUseCaseImpl
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCaseImpl
import com.grappim.domain.interactor.sales.*
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindGetBasketItemsUseCase(
        getBasketItemsUseCaseImpl: GetBasketItemsUseCaseImpl
    ): GetBasketItemsUseCase

    @Binds
    fun bindSubtractProductFromBasketUseCase(
        subtractProductFromBasketUseCaseImpl: SubtractProductFromBasketUseCaseImpl
    ): SubtractProductFromBasketUseCase

    @Binds
    fun bindAddProductToBasketUseCase(
        addProductToBasketUseCaseImpl: AddProductToBasketUseCaseImpl
    ): AddProductToBasketUseCase

    @Binds
    fun bindSearchProductsUseCase(
        searchProductsUseCaseImpl: SearchProductsUseCaseImpl
    ): SearchProductsUseCase

    @Binds
    fun bindGetCategoryListUseCase(
        getCategoryListUseCaseImpl: GetCategoryListUseCaseImpl
    ): GetCategoryListUseCase

    @Binds
    fun bindGetProductByBarcodeUseCase(
        getProductByBarcodeUseCaseImpl: GetProductByBarcodeUseCaseImpl
    ): GetProductByBarcodeUseCase

}