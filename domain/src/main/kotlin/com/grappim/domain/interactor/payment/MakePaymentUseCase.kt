package com.grappim.domain.interactor.payment

import com.grappim.common.lce.Try

interface MakePaymentUseCase {

    suspend fun execute(params: MakePaymentParams): Try<Unit, Throwable>

}