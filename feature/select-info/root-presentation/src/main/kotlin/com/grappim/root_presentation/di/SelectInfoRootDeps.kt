package com.grappim.root_presentation.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.AppRouter
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.navigation.CashierScreens
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator

interface SelectInfoRootDeps : ComponentDeps {

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository

    fun selectInfoFlowScreenNavigator(): SelectInfoFlowScreenNavigator
    fun appRouter(): AppRouter
    fun cashierScreens(): CashierScreens
}