package com.grappim.waybill.ui.details.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.interactor.waybill.ConductWaybillUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductsUseCase
import com.grappim.domain.interactor.waybill.RollbackWaybillUseCase
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillDetailsDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository
    fun flowRouter(): FlowRouter

    fun getWaybillProductsUseCase():GetWaybillProductsUseCase
    fun conductWaybillUseCase():ConductWaybillUseCase
    fun rollbackWaybillUseCase():RollbackWaybillUseCase

}