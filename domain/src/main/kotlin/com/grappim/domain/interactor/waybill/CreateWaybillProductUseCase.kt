package com.grappim.domain.interactor.waybill

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

class CreateWaybillProductUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<CreateWaybillProductUseCase.Params, BigDecimal>(ioDispatcher) {

    data class Params(
        val waybillId: Long,
        val barcode: String,
        val name: String,
        val purchasePrice: BigDecimal,
        val sellingPrice: BigDecimal,
        val amount: BigDecimal,
        val productId: Long
    )

    override fun execute(params: Params): Flow<Try<BigDecimal>> =
        waybillRepository.createWaybillProduct(params)
}