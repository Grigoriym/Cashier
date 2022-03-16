package com.grappim.product_category.presentation.root.di

import androidx.appcompat.app.AppCompatActivity
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface ProductCategoryRootDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun activity(): AppCompatActivity
    fun cashierScreens(): CashierScreens
    fun appRouter(): AppRouter
}