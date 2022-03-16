package com.grappim.cashbox.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.AppRouter
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.navigation.FlowRouter

interface SelectCashBoxDeps : ComponentDeps {

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun appRouter(): AppRouter
    fun flowRouter(): FlowRouter

}