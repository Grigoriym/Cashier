package com.grappim.waybill.ui.list.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator

interface WaybillListDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillScreenNavigator(): WaybillScreenNavigator
    fun waybillRepository(): WaybillRepository

}