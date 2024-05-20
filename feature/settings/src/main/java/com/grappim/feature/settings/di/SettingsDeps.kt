package com.grappim.feature.settings.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.domain.repository.GeneralRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface SettingsDeps : ComponentDeps {
    fun generalRepository(): GeneralRepository
    fun appRouter(): ActivityRouter
    fun cashierScreens(): CashierScreens
    fun generalStorage(): GeneralStorage
}
