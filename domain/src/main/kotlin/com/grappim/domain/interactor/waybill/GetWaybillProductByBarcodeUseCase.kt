package com.grappim.domain.interactor.waybill

import com.grappim.common.asynchronous.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillProductByBarcodeUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<GetWaybillProductByBarcodeUseCase.Params, WaybillProduct>(ioDispatcher) {

    data class Params(
        val barcode: String,
        val waybillId: Long
    )

    override fun execute(params: Params): Flow<Try<WaybillProduct>> =
        waybillRepository.getWaybillProductByBarcode(params)
}