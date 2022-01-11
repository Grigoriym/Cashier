package com.grappim.waybill.ui.search.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator

interface SearchWaybillProductDeps : ComponentDeps {

    fun waybillScreenNavigator(): WaybillScreenNavigator
    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun productsRepository(): ProductsRepository

}