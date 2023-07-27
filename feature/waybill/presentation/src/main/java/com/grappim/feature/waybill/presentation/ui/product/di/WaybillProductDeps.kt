package com.grappim.feature.waybill.presentation.ui.product.di

import android.content.Context
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillProductDeps : ComponentDeps {

    fun waybillRepository(): WaybillRepository
    fun waybillLocalRepository(): WaybillLocalRepository

    @ApplicationContext
    fun appContext(): Context
    fun flowRouter(): FlowRouter

}
