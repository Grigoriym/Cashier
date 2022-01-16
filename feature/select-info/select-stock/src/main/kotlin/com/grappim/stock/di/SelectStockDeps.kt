package com.grappim.stock.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator

interface SelectStockDeps : ComponentDeps {

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun selectInfoFlowScreenNavigator(): SelectInfoFlowScreenNavigator
}