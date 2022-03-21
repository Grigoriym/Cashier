package com.grappim.waybill.ui.scanner.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillScannerDeps : ComponentDeps {
    fun flowRouter(): FlowRouter
    fun waybillRepository(): WaybillRepository
    fun waybillLocalRepository(): WaybillLocalRepository
}