package com.grappim.domain.interactor.waybill

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Result
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import javax.inject.Inject

class UpdateWaybillProductUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<UpdateWaybillProductUseCase.Params, BigDecimal>(ioDispatcher) {

    data class Params(
        val waybillId: Int,
        val barcode: String,
        val name: String,
        val purchasePrice: BigDecimal,
        val sellingPrice: BigDecimal,
        val amount: BigDecimal,
        val productId: Long,
        val id: Long
    )

    override fun execute(params: Params): Flow<Result<BigDecimal>> =
        waybillRepository.updateWaybillProduct(params)
}