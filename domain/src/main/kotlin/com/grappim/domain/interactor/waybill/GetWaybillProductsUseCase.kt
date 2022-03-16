package com.grappim.domain.interactor.waybill

import androidx.paging.PagingData
import com.grappim.common.asynchronous.SimpleFlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWaybillProductsUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SimpleFlowUseCase<GetWaybillProductsUseCase.Params, PagingData<WaybillProduct>>(ioDispatcher) {

    data class Params(
        val waybillId: Long
    )

    override fun execute(params: Params): Flow<PagingData<WaybillProduct>> =
        waybillRepository.getProducts(params)
}