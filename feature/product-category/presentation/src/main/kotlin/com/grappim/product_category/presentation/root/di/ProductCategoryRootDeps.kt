package com.grappim.product_category.presentation.root.di

import androidx.appcompat.app.AppCompatActivity
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.product_category.domain.repository.ProductCategoryRepository

interface ProductCategoryRootDeps : ComponentDeps {

    fun productCategoryRepository(): ProductCategoryRepository
    fun activity(): AppCompatActivity
}