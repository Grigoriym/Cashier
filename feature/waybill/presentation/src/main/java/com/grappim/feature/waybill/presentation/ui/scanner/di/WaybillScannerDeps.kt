package com.grappim.feature.waybill.presentation.ui.scanner.di

import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.navigation.router.FlowRouter

interface WaybillScannerDeps : ComponentDeps {
    fun flowRouter(): FlowRouter
    fun waybillRepository(): WaybillRepository
    fun waybillLocalRepository(): WaybillLocalRepository
    fun productsRepository(): ProductsRepository
}
