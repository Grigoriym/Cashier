package com.grappim.productcategory.presentation.root.di

import androidx.appcompat.app.AppCompatActivity
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.screens.CashierScreens
import com.grappim.productcategory.domain.repository.ProductCategoryRepository

interface ProductCategoryRootDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun activity(): AppCompatActivity
    fun cashierScreens(): CashierScreens
    fun appRouter(): ActivityRouter
}
