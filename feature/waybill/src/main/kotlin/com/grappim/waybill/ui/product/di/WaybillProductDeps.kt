package com.grappim.waybill.ui.product.di

import android.content.Context
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillProductDeps : ComponentDeps {

    fun waybillRepository(): WaybillRepository
    fun waybillLocalRepository(): WaybillLocalRepository

    @ApplicationContext
    fun appContext(): Context
    fun flowRouter(): FlowRouter

}