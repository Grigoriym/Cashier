package com.grappim.stock.di

import com.grappim.core.di.components_deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.navigation.Navigator

interface SelectStockDeps : ComponentDeps {
    fun navigator(): Navigator
    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
}