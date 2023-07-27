package com.grappim.products.presentation.root.di

import com.grappim.feature.products.repository.di.ProductsRepositoryBindsModule
import com.grappim.products.network.di.ProductsApiModule
import dagger.Module

@Module(
    includes = [
        ProductsApiModule::class,
        ProductsRepositoryBindsModule::class,
    ]
)
interface ProductsAppModule
