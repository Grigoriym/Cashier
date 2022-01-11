package com.grappim.waybill.ui.product.di

import android.content.Context
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.WaybillRepository
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator

interface WaybillProductDeps : ComponentDeps {

    fun waybillRepository(): WaybillRepository

    @com.grappim.common.di.ApplicationContext
    fun appContext(): Context
    fun waybillScreenNavigator(): WaybillScreenNavigator

}