package com.grappim.domain.interactor.waybill

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.Result
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConductWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<ConductWaybillUseCase.Params, Waybill>(ioDispatcher) {

    data class Params(
        val waybill: Waybill
    )

    override fun execute(params: Params): Flow<Result<Waybill>> =
        waybillRepository.conductWaybill(params)
}