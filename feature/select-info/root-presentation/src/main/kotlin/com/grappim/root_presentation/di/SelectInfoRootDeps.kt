package com.grappim.root_presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens

interface SelectInfoRootDeps : ComponentDeps {

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun appRouter(): AppRouter
    fun cashierScreens(): CashierScreens
}