package com.grappim.waybill.ui.search.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.interactor.products.GetProductByBarcodeUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.interactor.waybill.GetWaybillProductByBarcodeUseCase
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository
import com.grappim.navigation.router.FlowRouter

interface SearchWaybillProductDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun productsRepository(): ProductsRepository
    fun flowRouter(): FlowRouter

    fun searchProductsUseCase(): SearchProductsUseCase
    fun getProductByBarcodeUseCase(): GetProductByBarcodeUseCase
    fun getWaybillProductByBarcodeUseCase(): GetWaybillProductByBarcodeUseCase

}