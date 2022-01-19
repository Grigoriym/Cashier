package com.grappim.repository.di

import com.grappim.domain.repository.*
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import com.grappim.product_category.repository.ProductCategoryRepositoryImpl
import com.grappim.repository.local.SelectCashBoxLocalRepositoryImpl
import com.grappim.repository.local.SelectStockLocalRepositoryImpl
import com.grappim.repository.local.WaybillLocalRepositoryImpl
import com.grappim.repository.remote.*
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository

    @Binds
    fun bindGeneralRepository(
        generalRepositoryImpl: GeneralRepositoryImpl
    ): GeneralRepository

    @Binds
    fun bindSelectInfoRepository(
        selectInfoRepositoryImpl: SelectInfoRemoteRepositoryImpl
    ): SelectInfoRemoteRepository

    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindWaybillRepository(
        waybillRepositoryImpl: WaybillRepositoryImpl
    ): WaybillRepository

    @Binds
    fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository

    @Binds
    fun bindWaybillLocalRepository(
        waybillLocalRepositoryImpl: WaybillLocalRepositoryImpl
    ): WaybillLocalRepository

    @Binds
    fun bindSelectStockRepository(
        selectStockLocalRepositoryImpl: SelectStockLocalRepositoryImpl
    ): SelectStockLocalRepository

    @Binds
    fun bindSelectCashBoxRepository(
        selectCashBoxLocalRepositoryImpl: SelectCashBoxLocalRepositoryImpl
    ): SelectCashBoxLocalRepository

    @Binds
    fun bindProductCategoryRepository(
        productCategoryRepositoryImpl: ProductCategoryRepositoryImpl
    ): ProductCategoryRepository
}