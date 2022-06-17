package com.grappim.feature.bag.presentation.di

import com.grappim.feature.bag.network.di.BagApiModule
import com.grappim.feature.bag.repository.di.BagBindsRepositoryModule
import dagger.Module

@Module(
    includes = [
        BagBindsRepositoryModule::class,
        BagApiModule::class
    ]
)
interface BagAppModule