package com.grappim.scanner.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens

interface ScannerDeps : ComponentDeps {
    fun cashierScreens(): CashierScreens
    fun appRouter(): AppRouter
}