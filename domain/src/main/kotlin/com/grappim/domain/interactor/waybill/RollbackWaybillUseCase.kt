package com.grappim.domain.interactor.waybill

import com.grappim.common.asynchronous.usecase.FlowUseCase
import com.grappim.common.asynchronous.di.IoDispatcher
import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RollbackWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<RollbackWaybillUseCase.Params, Unit>(ioDispatcher) {

    data class Params(
        val waybill: Waybill
    )

    override fun execute(params: Params): Flow<Try<Unit>> =
        waybillRepository.rollbackWaybill(params)
}