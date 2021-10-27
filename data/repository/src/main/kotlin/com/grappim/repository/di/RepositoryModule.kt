package com.grappim.repository.di

import com.grappim.domain.repository.*
import com.grappim.repository.remote.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
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
        selectInfoRepositoryImpl: SelectInfoRepositoryImpl
    ): SelectInfoRepository

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
    fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository
}