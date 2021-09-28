package com.grappim.domain.repository

import com.grappim.domain.base.Result
import com.grappim.domain.interactor.payment.MakePaymentUseCase
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    fun makePayment(params: MakePaymentUseCase.Params): Flow<Result<Unit>>

}