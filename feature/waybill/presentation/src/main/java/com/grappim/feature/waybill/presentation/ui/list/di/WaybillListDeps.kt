package com.grappim.feature.waybill.presentation.ui.list.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillListDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository
    fun flowRouter(): FlowRouter
}
