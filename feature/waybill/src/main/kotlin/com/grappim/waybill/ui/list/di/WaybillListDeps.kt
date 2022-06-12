package com.grappim.waybill.ui.list.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.interactor.waybill.CreateDraftWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillListPagingUseCase
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillListDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository
    fun flowRouter(): FlowRouter

    fun getWaybillListPagingUseCase(): GetWaybillListPagingUseCase
    fun createDraftWaybillUseCase(): CreateDraftWaybillUseCase

}