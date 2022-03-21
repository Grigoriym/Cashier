package com.grappim.product_category.domain.interactor

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<CreateProductCategoryUseCase.InParams, Unit>(ioDispatcher) {

    data class InParams(
        val name: String
    )

    override fun execute(params: InParams): Flow<Try<Unit>> =
        productCategoryRepository.createProductCategory(params)

}