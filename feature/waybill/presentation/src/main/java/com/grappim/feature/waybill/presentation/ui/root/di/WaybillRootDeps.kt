package com.grappim.feature.waybill.presentation.ui.root.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.feature.products.domain.repository.ProductsRepository
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import com.grappim.feature.waybill.domain.repository.WaybillRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface WaybillRootDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun productsRepository(): ProductsRepository

    @ApplicationContext
    fun appContext(): Context

    fun activity(): AppCompatActivity
    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
}
