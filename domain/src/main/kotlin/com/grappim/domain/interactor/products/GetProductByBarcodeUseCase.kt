package com.grappim.domain.interactor.products

import com.grappim.common.asynchronous.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByBarcodeUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<GetProductByBarcodeUseCase.Params, Product>(ioDispatcher) {

    data class Params(
        val barcode: String
    )

    override fun execute(params: Params): Flow<Try<Product>> =
        waybillRepository.getProductByBarcode(params)
}