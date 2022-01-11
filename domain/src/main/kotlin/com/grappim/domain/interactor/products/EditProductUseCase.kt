package com.grappim.domain.interactor.products

import com.grappim.common.asynchronous.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

class EditProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<EditProductUseCase.Params, Unit>(ioDispatcher) {

    data class Params(
        val name: String,
        val barcode: String,
        val sellingPrice: BigDecimal,
        val purchasePrice: BigDecimal,
        val amount: BigDecimal,
        val unit: ProductUnit,
        val productMerchantId: String,
        val productCreatedOn: String,
        val productId: Long,
        val productStockId: String,
        val categoryId: Long,
        val category: String
    )

    override fun execute(params: Params): Flow<Try<Unit>> =
        productsRepository.updateProduct(params)

}

