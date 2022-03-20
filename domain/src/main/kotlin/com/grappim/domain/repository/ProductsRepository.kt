package com.grappim.domain.repository

import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.sales.SearchProductsUseCase
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun createProduct(
        params: CreateProductUseCase.Params
    ): Flow<Try<Unit>>

    fun updateProduct(
        params: EditProductUseCase.Params
    ): Flow<Try<Unit>>

    fun searchProducts(
        params: SearchProductsUseCase.Params
    ): Flow<PagingData<Product>>

    suspend fun filterProducts(
        offset: Long,
        limit: Long
    ): List<Product>

    suspend fun insertProducts(newProducts: List<Product>)
}