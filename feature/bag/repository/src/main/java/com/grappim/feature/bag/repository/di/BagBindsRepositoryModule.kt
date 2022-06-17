package com.grappim.feature.bag.repository.di

import com.grappim.feature.bag.domain.BagRepository
import com.grappim.feature.bag.repository.BagRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface BagBindsRepositoryModule {
    @Binds
    fun bindBagRepository(
        basketRepositoryImpl: BagRepositoryImpl
    ): BagRepository
}