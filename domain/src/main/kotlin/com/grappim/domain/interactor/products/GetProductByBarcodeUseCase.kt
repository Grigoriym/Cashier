package com.grappim.domain.interactor.products

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Try
import com.grappim.domain.di.IoDispatcher
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