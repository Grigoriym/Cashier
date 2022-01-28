package com.grappim.domain.interactor.products

import com.grappim.common.asynchronous.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.repository.GeneralRepository
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryListInteractor @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val generalRepository: GeneralRepository,
    private val productCategoryRepository: ProductCategoryRepository
) : FlowUseCase<GetCategoryListInteractor.Params, List<ProductCategory>>(ioDispatcher) {

    data class Params(
        val sendDefaultCategory: Boolean = true
    )

    override fun execute(params: Params): Flow<Try<List<ProductCategory>>> =
        generalRepository.getCategories(params)

    fun getSimpleCategoryList(params: Params): Flow<List<ProductCategory>> =
        generalRepository.getCategories2(params)
}