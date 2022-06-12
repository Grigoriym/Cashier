package com.grappim.domain.interactor.sales

import androidx.paging.PagingData
import com.grappim.domain.model.product.Product
import kotlinx.coroutines.flow.Flow

interface SearchProductsUseCase {
    fun execute(params: SearchProductsParams): Flow<PagingData<Product>>
}