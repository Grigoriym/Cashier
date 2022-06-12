package com.grappim.product_category.domain.di

import com.grappim.product_category.domain.interactor.*
import dagger.Binds
import dagger.Module

@Module
interface ProductCategoryInteractorModule {

    @Binds
    fun bindCreateProductCategoryUseCase(
        createProductCategoryUseCaseImpl: CreateProductCategoryUseCaseImpl
    ): CreateProductCategoryUseCase

    @Binds
    fun bindEditProductCategoryUseCase(
        editProductCategoryUseCaseImpl: EditProductCategoryUseCaseImpl
    ): EditProductCategoryUseCase

    @Binds
    fun bindGetProductCategoriesUseCase(
        getProductCategoriesUseCaseImpl: GetProductCategoriesUseCaseImpl
    ): GetProductCategoriesUseCase
}