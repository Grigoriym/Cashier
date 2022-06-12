package com.grappim.domain.repository

import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.products.CreateProductParams
import com.grappim.domain.interactor.products.EditProductParams
import com.grappim.domain.interactor.sales.SearchProductsParams
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun createProduct(
        params: CreateProductParams
    ): Try<Unit, Throwable>

    suspend fun updateProduct(
        params: EditProductParams
    ): Try<Unit, Throwable>

    fun searchProducts(
        params: SearchProductsParams
    ): Flow<PagingData<Product>>

    suspend fun filterProducts(
        offset: Long,
        limit: Long
    ): List<Product>

    suspend fun insertProducts(newProducts: List<Product>)
}