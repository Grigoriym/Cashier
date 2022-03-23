package com.grappim.feature.settings.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.GeneralRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface SettingsDeps : ComponentDeps {
    fun generalRepository(): GeneralRepository
    fun appRouter(): ActivityRouter
    fun cashierScreens(): CashierScreens
}