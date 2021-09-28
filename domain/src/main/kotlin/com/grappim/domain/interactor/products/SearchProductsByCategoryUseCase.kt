package com.grappim.domain.interactor.products

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Result
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.GeneralRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsByCategoryUseCase @Inject constructor(
    private val generalRepository: GeneralRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<SearchProductsByCategoryUseCase.Params, List<Product>>(ioDispatcher) {

    data class Params(
        val category: Category
    )

    override fun execute(params: Params): Flow<Result<List<Product>>> =
        generalRepository.getProductsByCategory(params)

}