package com.grappim.feature.products.domain.repository

import androidx.paging.PagingData
import com.grappim.common.lce.Try
import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.interactor.createProduct.CreateProductParams
import com.grappim.feature.products.domain.interactor.editProduct.EditProductParams
import com.grappim.feature.products.domain.interactor.getProductByBarcode.GetProductBarcodeParams
import com.grappim.feature.products.domain.interactor.getProductsByQuery.GetProductsByQueryParams
import com.grappim.feature.products.domain.interactor.searchProducts.SearchProductsParams
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProductByBarcode(
        params: GetProductBarcodeParams
    ): Try<Product, Throwable>

    fun getProductsByQuery(
        params: GetProductsByQueryParams
    ): Flow<List<Product>>

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