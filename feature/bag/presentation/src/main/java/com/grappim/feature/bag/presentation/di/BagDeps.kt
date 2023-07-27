package com.grappim.feature.bag.presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.feature.bag.domain.BagRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface BagDeps : ComponentDeps {

    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
    fun basketRepository(): BagRepository

}
