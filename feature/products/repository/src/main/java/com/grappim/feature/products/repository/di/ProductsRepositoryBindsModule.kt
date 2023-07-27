package com.grappim.feature.products.repository.di

import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.feature.products.repository.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface ProductsRepositoryBindsModule {

    @Binds
    fun bindProductsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}
