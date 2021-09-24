package com.grappim.cashier.domain.waybill

import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.platform.FlowUseCase
import com.grappim.cashier.di.modules.IoDispatcher
import com.grappim.cashier.domain.extension.WithoutParams
import com.grappim.cashier.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<WithoutParams, Waybill>(ioDispatcher) {

    override fun execute(parameters: WithoutParams): Flow<Resource<Waybill>> =
        waybillRepository.createDraftWaybill()

}