package com.grappim.waybill.ui.details.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.FlowRouter

interface WaybillDetailsDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository
    fun flowRouter(): FlowRouter

}