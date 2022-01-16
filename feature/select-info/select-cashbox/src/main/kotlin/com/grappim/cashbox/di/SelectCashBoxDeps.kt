package com.grappim.cashbox.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator

interface SelectCashBoxDeps : ComponentDeps {

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun selectInfoFlowScreenNavigator(): SelectInfoFlowScreenNavigator

}