package com.grappim.cashbox.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.interactor.cashier.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashier.SaveCashBoxUseCase
import com.grappim.navigation.router.ActivityRouter
import com.grappim.domain.repository.SelectInfoRemoteRepository
import com.grappim.domain.repository.local.SelectCashBoxLocalRepository
import com.grappim.navigation.router.FlowRouter

interface SelectCashBoxDeps : ComponentDeps {

    fun selectCashBoxLocalRepository(): SelectCashBoxLocalRepository
    fun selectInfoRemoteRepository(): SelectInfoRemoteRepository
    fun appRouter(): ActivityRouter
    fun flowRouter(): FlowRouter

    fun getCashBoxesUseCase(): GetCashBoxesUseCase
    fun saveCashBoxUseCase(): SaveCashBoxUseCase

}