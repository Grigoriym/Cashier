package com.grappim.cashier.di.modules

import com.grappim.cashier.data.repository.AuthRepositoryImpl
import com.grappim.cashier.data.repository.GeneralRepositoryImpl
import com.grappim.cashier.data.repository.SelectInfoRepositoryImpl
import com.grappim.cashier.data.repository.WaybillRepositoryImpl
import com.grappim.cashier.domain.repository.AuthRepository
import com.grappim.cashier.domain.repository.GeneralRepository
import com.grappim.cashier.domain.repository.SelectInfoRepository
import com.grappim.cashier.domain.repository.WaybillRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGeneralRepository(
        generalRepositoryImpl: GeneralRepositoryImpl
    ): GeneralRepository

    @Binds
    abstract fun bindSelectInfoRepository(
        selectInfoRepositoryImpl: SelectInfoRepositoryImpl
    ): SelectInfoRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindWaybillRepository(
        waybillRepositoryImpl: WaybillRepositoryImpl
    ): WaybillRepository
}