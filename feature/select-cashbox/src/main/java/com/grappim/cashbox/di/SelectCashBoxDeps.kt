package com.grappim.cashbox.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.navigation.directions.select_cashier.SelectCashBoxNavigator

interface SelectCashBoxDeps : ComponentDeps {

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository

    fun selectCashBoxNavigator(): SelectCashBoxNavigator
}