package com.grappim.cashier.domain.payment

import com.grappim.cashier.core.functional.Either
import com.grappim.cashier.domain.repository.GeneralRepository
import com.grappim.cashier.ui.paymentmethod.PaymentMethod
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val generalRepository: GeneralRepository
) {

    suspend operator fun invoke(paymentMethod: PaymentMethod): Either<Throwable, Unit> =
        generalRepository.makePayment(paymentMethod)
}