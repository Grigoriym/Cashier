package com.grappim.domain.interactor.waybill

import com.grappim.common.lce.Try
import com.grappim.domain.model.waybill.Waybill

interface CreateDraftWaybillUseCase {
    suspend fun createDraftWaybill(): Try<Waybill, Throwable>
}