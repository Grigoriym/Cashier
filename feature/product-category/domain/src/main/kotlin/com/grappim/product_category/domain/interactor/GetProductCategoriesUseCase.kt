package com.grappim.product_category.domain.interactor

import com.grappim.common.asynchronous.usecase.SimpleFlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.NoParams
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductCategoriesUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productCategoryRepository: ProductCategoryRepository
) : SimpleFlowUseCase<NoParams, List<ProductCategory>>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<List<ProductCategory>> =
        productCategoryRepository.getCategoriesFlow2()

    fun categoriesFlow(): Flow<List<ProductCategory>> =
        productCategoryRepository.categoriesFlow()

}