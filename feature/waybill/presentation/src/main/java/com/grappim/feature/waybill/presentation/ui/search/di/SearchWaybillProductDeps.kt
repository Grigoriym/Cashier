package com.grappim.feature.waybill.presentation.ui.search.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.navigation.router.FlowRouter

interface SearchWaybillProductDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun productsRepository(): ProductsRepository
    fun flowRouter(): FlowRouter
}
