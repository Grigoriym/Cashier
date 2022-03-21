package com.grappim.product_category.domain.interactor

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.product_category.domain.repository.ProductCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditProductCategoryUseCase @Inject constructor(
    private val productCategoryRepository: ProductCategoryRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<EditProductCategoryUseCase.Params, Unit>(ioDispatcher) {

    data class Params(
        val newName: String,
        val productCategory: ProductCategory
    )

    override fun execute(params: Params): Flow<Try<Unit>> =
        productCategoryRepository.editProductCategory(params)

}