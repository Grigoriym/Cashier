package com.grappim.stock.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository

interface SelectStockDeps : ComponentDeps {
    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
}