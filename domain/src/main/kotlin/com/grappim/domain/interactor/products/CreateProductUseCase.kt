package com.grappim.domain.interactor.products

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<CreateProductUseCase.Params, Unit>(ioDispatcher) {

    override fun execute(params: Params): Flow<Try<Unit>> =
        productsRepository.createProduct(params)

    data class Params(
        val name: String,
        val stockId: String,
        val merchantId: String,
        val unit: String,
        val purchasePrice: BigDecimal,
        val sellingPrice: BigDecimal,
        val amount: BigDecimal,
        val barcode: String,
        val categoryName: String,
        val categoryId: Long
    )
}