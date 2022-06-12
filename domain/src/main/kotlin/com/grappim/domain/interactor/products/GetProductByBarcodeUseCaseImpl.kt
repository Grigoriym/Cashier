package com.grappim.domain.interactor.products

import com.grappim.common.lce.Try
import com.grappim.domain.model.product.Product
import com.grappim.domain.repository.WaybillRepository
import javax.inject.Inject

class GetProductByBarcodeUseCaseImpl @Inject constructor(
    private val waybillRepository: WaybillRepository
) : GetProductByBarcodeUseCase {

    override suspend fun execute(params: GetProductBarcodeParams): Try<Product, Throwable> =
        waybillRepository.getProductByBarcode(params)
}