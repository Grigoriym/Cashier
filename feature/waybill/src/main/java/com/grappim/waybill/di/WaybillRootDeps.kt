package com.grappim.waybill.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository

interface WaybillRootDeps : ComponentDeps {

    fun waybillScreenNavigator(): WaybillScreenNavigator

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

}