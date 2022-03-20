package com.grappim.menu.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens

interface MenuDeps : ComponentDeps {

    fun generalStorage(): GeneralStorage
    fun appRouter(): AppRouter
    fun cashierScreens(): CashierScreens

}