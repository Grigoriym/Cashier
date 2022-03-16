package com.grappim.payment_method.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.payment.MakePaymentUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
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
    private val makePaymentUseCase: MakePaymentUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat
) : PaymentMethodViewModel() {

    override val paymentItems = paymentMethodItemGenerator.paymentMethodItems

    override val basketCount: StateFlow<String> =
        getAllBasketProductsUseCase.invoke(withoutParams()).map { list ->
            list.map {
                it.basketCount
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = "0"
        )

    override val basketSum: StateFlow<String> =
        getAllBasketProductsUseCase.invoke(withoutParams()).map { list ->
            list.map {
                it.sellingPrice * it.basketCount
            }.sumOf {
                it
            }
        }.map {
            dfSimple.format(it)
        }.stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = "0"
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