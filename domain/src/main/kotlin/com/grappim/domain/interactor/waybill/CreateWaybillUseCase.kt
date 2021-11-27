package com.grappim.domain.interactor.waybill

import com.grappim.domain.base.FlowUseCase
import com.grappim.domain.base.NoParams
import com.grappim.domain.base.Try
import com.grappim.domain.di.IoDispatcher
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.WaybillRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateWaybillUseCase @Inject constructor(
    private val waybillRepository: WaybillRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<NoParams, Waybill>(ioDispatcher) {

    override fun execute(params: NoParams): Flow<Try<Waybill>> =
        waybillRepository.createDraftWaybill()

}