package com.grappim.feature.selectinfo.rootpresentation.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface SelectInfoRootDeps : ComponentDeps {

    fun selectStockLocalRepository(): SelectStockLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun activityRouter(): ActivityRouter
    fun cashierScreens(): CashierScreens
    fun generalStorage(): GeneralStorage
}
