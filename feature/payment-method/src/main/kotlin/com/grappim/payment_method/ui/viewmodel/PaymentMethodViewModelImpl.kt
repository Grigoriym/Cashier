package com.grappim.payment_method.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.calculations.bigDecimalZero
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.basket.GetBasketItemsUseCase
import com.grappim.domain.interactor.payment.MakePaymentUseCase
import com.grappim.payment_method.helper.PaymentMethodItemGenerator
import com.grappim.payment_method.model.PaymentMethod
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

class PaymentMethodViewModelImpl @Inject constructor(
    paymentMethodItemGenerator: PaymentMethodItemGenerator,
    getBasketItemsUseCase: GetBasketItemsUseCase,
    private val makePaymentUseCase: MakePaymentUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : PaymentMethodViewModel() {

    override val paymentItems = paymentMethodItemGenerator.paymentMethodItems

    override val basketCount: StateFlow<String> = getBasketItemsUseCase
        .invoke(withoutParams()).map { list ->
            list.map {
                it.sellingPrice
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = dfSimple.format(bigDecimalZero())
        )

    override val basketSum: StateFlow<String> = getBasketItemsUseCase
        .invoke(withoutParams()).map { list ->
            list.map {
                it.sellingPrice * it.amount
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = dfSimple.format(bigDecimalZero())
        )

    override fun makePayment(paymentMethod: PaymentMethod) {
        viewModelScope.launch {
            makePaymentUseCase.invoke(MakePaymentUseCase.Params(paymentMethod.type))
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Success -> {
                            flowRouter.returnToSalesFromPaymentMethod()
                        }
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                    }
                }
        }
    }
}