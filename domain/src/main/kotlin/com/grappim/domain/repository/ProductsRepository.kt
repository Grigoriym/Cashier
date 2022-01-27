package com.grappim.domain.repository

import com.grappim.common.lce.Try
import com.grappim.domain.interactor.products.CreateProductUseCase
import com.grappim.domain.interactor.products.EditProductUseCase
import com.grappim.domain.interactor.sales.AddProductToBasketUseCase
import com.grappim.domain.interactor.sales.RemoveProductUseCase
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

    fun getBagProducts(): Flow<Try<List<Product>>>

    suspend fun addBasketProduct(params: AddProductToBasketUseCase.Params)
    suspend fun removeBasketProduct(params: RemoveProductUseCase.Params)

    fun getAllBasketProducts(): Flow<List<BasketProduct>>

    fun getProducts(): Flow<List<Product>>

    suspend fun deleteBasketProducts()

    fun searchProducts(
        params: SearchProductsUseCase.Params
    ): Flow<List<Product>>

    suspend fun filterProducts(
        offset: Long,
        limit: Long
    ): List<Product>

    suspend fun insertProducts(newProducts: List<Product>)
}