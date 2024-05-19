package com.grappim.menu.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface MenuDeps : ComponentDeps {
    fun generalStorage(): GeneralStorage
    fun appRouter(): ActivityRouter
    fun cashierScreens(): CashierScreens
    fun featureToggleLocalRepository(): FeatureToggleLocalRepository
}
