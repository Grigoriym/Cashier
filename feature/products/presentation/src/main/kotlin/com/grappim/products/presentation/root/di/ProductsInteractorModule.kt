package com.grappim.products.presentation.root.di

import com.grappim.domain.interactor.products.*
import dagger.Binds
import dagger.Module

@Module
interface ProductsInteractorModule {
    @Binds
    fun bindCreateProductUseCase(
        createProductUseCaseImpl: CreateProductUseCaseImpl
    ): CreateProductUseCase

    @Binds
    fun bindEditProductUseCase(
        editProductUseCaseImpl: EditProductUseCaseImpl
    ): EditProductUseCase

    @Binds
    fun bindGetProductsByQueryUseCase(
        getProductsByQueryUseCaseImpl: GetProductsByQueryUseCaseImpl
    ): GetProductsByQueryUseCase
}