package com.grappim.payment_method

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.calculations.DecimalFormatSimple
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.base.Try
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.payment.MakePaymentUseCase
import com.grappim.domain.interactor.sales.GetAllBasketProductsUseCase
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    paymentMethodItemGenerator: PaymentMethodItemGenerator,
    private val makePaymentUseCase: MakePaymentUseCase,
    getAllBasketProductsUseCase: GetAllBasketProductsUseCase,
    @DecimalFormatSimple private val dfSimple: DecimalFormat,
    private val navigator: Navigator
) : ViewModel() {

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
        navigator.popBackStack()
    }

    fun makePayment(paymentMethod: PaymentMethod) {
        viewModelScope.launch {
            makePaymentUseCase.invoke(MakePaymentUseCase.Params(paymentMethod.type))
                .collect {
                    _paymentStatus.value = it
                    when (it) {
                        is Try.Success -> {
                            navigator.navigateToFlow(NavigationFlow.PaymentMethodToSales)
                        }
                    }
                }
        }
    }
}