package com.grappim.waybill.ui.list.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.di.WaybillScreenNavigator

interface WaybillListDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillScreenNavigator(): WaybillScreenNavigator
    fun waybillRepository(): WaybillRepository

}