package com.grappim.bag.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.repository.ProductsRepository

interface BagDeps : ComponentDeps {

    fun bagScreenNavigator(): BagScreenNavigator

    fun productsRepository():ProductsRepository

}