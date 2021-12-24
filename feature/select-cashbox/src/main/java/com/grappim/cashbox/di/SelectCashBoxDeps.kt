package com.grappim.cashbox.di

import com.grappim.core.di.components_deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.navigation.Navigator

interface SelectCashBoxDeps : ComponentDeps {
    fun navigator(): Navigator
    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
}