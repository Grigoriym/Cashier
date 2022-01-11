package com.grappim.payment_method.ui

import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.core.BaseViewModel
import com.grappim.domain.interactor.payment.MakePaymentUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
import com.grappim.payment_method.helper.PaymentMethodItemGenerator
import com.grappim.payment_method.model.PaymentMethod
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

class PaymentMethodViewModel @Inject constructor(
    paymentMethodItemGenerator: PaymentMethodItemGenerator,
    private val makePaymentUseCase: MakePaymentUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    private val paymentMethodScreenNavigator: PaymentMethodScreenNavigator
) : BaseViewModel() {

    private val _paymentStatus = MutableStateFlow<Try<Unit>>(
        Try.Initial
    )
    val paymentStatus: StateFlow<Try<Unit>>
        get() = _paymentStatus.asStateFlow()

    val paymentItems = paymentMethodItemGenerator.paymentMethodItems

    val basketCount: StateFlow<String> =
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

    val basketSum: StateFlow<String> =
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

    fun onBackPressed() {
        paymentMethodScreenNavigator.goBack()
    }

    fun makePayment(paymentMethod: PaymentMethod) {
        viewModelScope.launch {
            makePaymentUseCase.invoke(MakePaymentUseCase.Params(paymentMethod.type))
                .collect {
                    _paymentStatus.value = it
                    when (it) {
                        is Try.Success -> {
                            paymentMethodScreenNavigator.fromPaymentMethodToSales()
                        }
                    }
                }
        }
    }
}