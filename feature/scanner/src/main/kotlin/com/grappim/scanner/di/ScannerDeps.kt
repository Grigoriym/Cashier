package com.grappim.scanner.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface ScannerDeps : ComponentDeps {
    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
}
