package com.grappim.cashier.feature.productcategory.presentation.root.di

import androidx.appcompat.app.AppCompatActivity
import com.grappim.cashier.common.di.deps.ComponentDeps
import com.grappim.cashier.feature.productcategory.domain.repository.ProductCategoryRepository
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens

interface ProductCategoryRootDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun activity(): AppCompatActivity
    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
}
