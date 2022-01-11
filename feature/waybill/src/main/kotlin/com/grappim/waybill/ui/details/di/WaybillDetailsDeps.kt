package com.grappim.waybill.ui.details.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator

interface WaybillDetailsDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillScreenNavigator(): WaybillScreenNavigator
    fun waybillRepository(): WaybillRepository

}