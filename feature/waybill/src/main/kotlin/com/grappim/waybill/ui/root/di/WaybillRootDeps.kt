package com.grappim.waybill.ui.root.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.grappim.common.di.ApplicationContext
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.repository.ProductsRepository
import com.grappim.domain.repository.WaybillRepository
import com.grappim.domain.repository.local.WaybillLocalRepository

interface WaybillRootDeps : ComponentDeps {

    fun waybillLocalRepository(): WaybillLocalRepository
    fun waybillRepository(): WaybillRepository

    fun productsRepository(): ProductsRepository

    @ApplicationContext
    fun appContext(): Context

    fun activity(): AppCompatActivity

}