package com.grappim.domain.interactor.products

import com.grappim.common.asynchronous.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.product.Category
import com.grappim.domain.repository.GeneralRepository
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Deprecated(
    message = "need to be refactored"
)
class GetCategoryListUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val generalRepository: GeneralRepository,
    private val productCategoryRepository: ProductCategoryRepository
) : FlowUseCase<GetCategoryListUseCase.Params, List<Category>>(ioDispatcher) {

    data class Params(
        val sendDefaultCategory: Boolean = true
    )

    override fun execute(params: Params): Flow<Try<List<Category>>> =
        generalRepository.getCategories(params)

    fun execute2(params: Params): Flow<List<Category>> =
        generalRepository.getCategories2(params)

    fun execute3(params: Params):Flow<List<ProductCategory>> =
        productCategoryRepository.getCategoriesFlow2()
}