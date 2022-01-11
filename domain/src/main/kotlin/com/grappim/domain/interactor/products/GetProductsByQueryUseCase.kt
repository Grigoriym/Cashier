package com.grappim.domain.interactor.products

import com.grappim.common.asynchronous.SimpleFlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.GeneralRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByQueryUseCase @Inject constructor(
    private val generalRepository: GeneralRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SimpleFlowUseCase<GetProductsByQueryUseCase.Params, List<Product>>(ioDispatcher) {

    data class Params(
        val category: Category?,
        val query: String
    )

    override fun execute(params: Params): Flow<List<Product>> =
        generalRepository.getProductsByQuery(params)
}