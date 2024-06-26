package com.grappim.stock.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.router.FlowRouter

interface SelectStockDeps : ComponentDeps {

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun flowRouter(): FlowRouter
    fun generalStorage(): GeneralStorage
}
