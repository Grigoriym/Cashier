package com.grappim.stock.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.navigation.directions.select_stock.SelectStockScreenNavigator

interface SelectStockDeps : ComponentDeps {

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository

    fun selectStockScreenNavigator(): SelectStockScreenNavigator
}